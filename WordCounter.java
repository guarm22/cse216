import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;


public class WordCounter {

        // The following are the ONLY variables we will modify for grading.
        // The rest of your code must run with no changes.
        public static final Path FOLDER_OF_TEXT_FILES  = Paths.get("textInput"); // path to the folder where input text files are located
        public static final Path WORD_COUNT_TABLE_FILE = Paths.get("txtOutput2.txt"); // path to the output plain-text (.txt) file
        public static final int  NUMBER_OF_THREADS     = 2;                // max. number of threads to spawn 
         
        public static void main(String... args) {
        	makeTable();
        }
		private static void makeTable() {
			createData();
		}
		
		private static void createTable(TreeMap<String, Integer> finalTotal, List<TreeMap<String, Integer>> listOfTm, 
				List<String> sortedWords, List<String> fileNames) {	
			int longestFileName = getLongestFileName(fileNames).length() < 10 ? 10 : getLongestFileName(fileNames).length();
			Integer longestKey = getLongestKey(finalTotal).length();
			
			String finalData = "";
			for(int j=-1; j<=fileNames.size(); j++) {
				if(j==-1) {
					for(int i=0; i<=longestKey; i++) {
						finalData+=" ";
					}
					continue;
				}
				if(j!=fileNames.size()) {
					finalData+=fileNames.get(j);
					for(int i=0; i<=longestFileName-(fileNames.get(j).length())+1; i++) {
						finalData+=" ";
					}
				}
				else {
					finalData += "total \n";
				}
			}
			for(int i=0; i<sortedWords.size(); i++) {
				String s = sortedWords.get(i);
				finalData += s;
				for(int j=0; j<longestKey - s.length()+1; j++) {
					finalData+=" ";
				}
				for(int j=0; j<listOfTm.size(); j++) {
					if(listOfTm.get(j).containsKey(s)) {
						
						int skip = listOfTm.get(j).get(s).toString().length()-1;
						finalData+= listOfTm.get(j).get(s);
						for(int k=0; k <= longestFileName-skip; k++) {
							finalData+=" ";
						}
					}
					else {
						finalData+="0";
						for(int k=0; k <= longestFileName; k++) {
							finalData+=" ";
						}
					}
				}
				finalData  += finalTotal.get(s) + "\n"; 
			}
			System.out.println(finalData);
			save(finalData);
		}

		private static void save(String finalData) {
		    try {
		    	FileWriter fileWriter = new FileWriter(WORD_COUNT_TABLE_FILE.toFile().getCanonicalPath());
		        PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.print(finalData);
				printWriter.close();
			} catch (IOException e) {}
		}
		
		private static void createTotal(List<TreeMap<String, Integer>> listOfTm, List<String> fileNames) {
			HashMap<String, Integer> total = new HashMap<>();
			for(int i=0; i<listOfTm.size(); i++) {
				Iterator<String> iter = listOfTm.get(i).keySet().iterator();
				
				for(int j=0; j<listOfTm.get(i).size(); j++) {
					String s = iter.next();
					if(total.containsKey(s)) {
						total.put(s, listOfTm.get(i).get(s) + total.get(s));
					}
					else {
						total.put(s, listOfTm.get(i).get(s));
					}
				}	
			}
			List<String> sortedWords = total.keySet().stream().map(e -> e).collect(Collectors.toList());
			Collections.sort(sortedWords);
			TreeMap<String, Integer> finalTotal = new TreeMap<>(total);
			createTable(finalTotal, listOfTm, sortedWords, fileNames);
		}

		private static String getLongestFileName(List<String> fileNames) {
			String longest = "";
		    for(String key : fileNames) {
		       if(key.length() > longest.length()) {
		           longest = key;
		       }
		    }
		    return longest;
		}

		private static String getLongestKey(TreeMap<String, Integer> tm) {
			String longest = "";
		    for(String key : tm.keySet()) {
		        if(key.length() > longest.length()) {
		        	longest = key;
		        }
		    }
		    return longest;
		}

		private synchronized static void createData() {
			ExecutorService executorService=null;
			if(NUMBER_OF_THREADS>0) {executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);}
			else if(NUMBER_OF_THREADS<0) {throw new IllegalArgumentException("Cannot have less than 0 threads."); }
			
			List<File> listOfFiles = null;
			try {
				listOfFiles = Files.walk(FOLDER_OF_TEXT_FILES).skip(1)
						.filter(e -> e.toFile().getAbsolutePath().endsWith(".txt"))
						.map(e -> new File(e.toFile().getAbsolutePath()))
						.collect(Collectors.toList());
			} catch (IOException e) {e.printStackTrace(); }
			
			Iterator<File> fileIter = listOfFiles.iterator(); 
			List<String> fileNames = new ArrayList<>();		
			HashMap<String, TreeMap<String,Integer>> test1 = new HashMap<>();
			
			Runnable r = () -> {
				Thread.currentThread().setPriority(10);
				long first = System.currentTimeMillis();
				File f = fileIter.next();
				try {
					HashMap<String, Integer> hm = new HashMap<>();
					Scanner reader = new Scanner(f);
					
					while(reader.hasNext()) {
						String word = reader.next().replaceAll("[.,?!;:]", "").toLowerCase();
						if(hm.containsKey(word)) {
							hm.replace(word, hm.get(word)+1);
						}
						else {hm.put(word, 1); }
					}
					reader.close();
					TreeMap<String, Integer> tm = new TreeMap<>(hm);
					
					String[] fileSplit = f.getAbsolutePath().split("\\\\");
					test1.put(fileSplit[fileSplit.length-1].replace(".txt", ""), tm);
					fileNames.add(fileSplit[fileSplit.length-1].replace(".txt", ""));
					
					long second = System.currentTimeMillis();
					System.out.println(Thread.currentThread() + " time taken to read " + 
							fileSplit[fileSplit.length-1].replace(".txt", "") + ": " + (second-first) + "ms");
				} catch(IOException e) {}
			};
			long l1 = System.currentTimeMillis();
			if(NUMBER_OF_THREADS < 2) {
				for(int i=0; i<listOfFiles.size(); i++) { r.run(); }
			}
			else {
				for(int i=0; i<listOfFiles.size(); i++) {
					try {
						Thread.sleep(20); //this causes the individual threads to run faster, but the 
					} catch (InterruptedException e) {} //overall runtime to be slightly slower with the
					executorService.submit(r);          // added side effect of less bugs!
				}
				executorService.shutdown();
				try {
					executorService.awaitTermination(30, TimeUnit.SECONDS);
				} catch (InterruptedException e) {
					e.printStackTrace(); }
			}
			System.out.println("\nTotal time taken to read " + fileNames.size() + " files using "+ NUMBER_OF_THREADS +" threads: " + 
					(System.currentTimeMillis() - l1) + "ms");
			Collections.sort(fileNames);
			
			TreeMap<String, TreeMap<String, Integer>> sorted = new TreeMap<>();
			sorted.putAll(test1);
			
			List<TreeMap<String, Integer>> finalList = sorted.values().stream().map(e->e)
					.collect(Collectors.toList());
			createTotal(finalList,fileNames);
		}
    }