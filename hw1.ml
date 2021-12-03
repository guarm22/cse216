let rec pow x n = if n=0 then 1 else x * pow (x) (n-1);;

let rec float_pow x n = if n=0 then 1.0 else x *. float_pow (x) (n-1);;

let rec compress = function
  | [] -> []
  | h::[] -> [h]
  | h::(a::_ as t) -> if h = a then compress t else h::compress t
;;

let rec remove_if list_to_check func = match list_to_check with
    | [] -> []
    | h::t -> if (func h) then (remove_if t func) else h :: (remove_if t func) 
;;

let rec slice lst a b =
  let rec keep n = function
  | [] -> []
  | h::t -> if (n=0 || a > b) then [] else h :: keep (n-1) t
  in
    let rec remove n = function
      | [] -> []
      | h :: t as l -> if (n=0 || a > b) then l else remove (n-1) t
    in
    keep (b - a) (remove a lst)
;;  

let goldbachpair a =
  let is_prime n =
    let n = max n (-n) in
    let rec is_not_divisor d =
      d * d > n || (n mod d <> 0 && is_not_divisor (d+1)) in is_not_divisor 2
     in
     let rec helper b =
    if is_prime b && is_prime (a - b) then (b, a-b)
    else helper (b+1) in
     helper 2
;;

let rec equiv_on f g = function
    | [] -> true
    | h :: t -> if f h = g h then equiv_on f g t else false
;;

let rec pairwisefilter f = function
    | [] -> []
    | h::[] -> [h]
    | h::b::t -> (f h b)::(pairwisefilter f t)
;;

let polynomial lst n =  
  let rec helper sum = function
      | [] -> sum 
      | h::t -> let coefficient,exponent = h in sum + helper(coefficient * (pow n exponent)) t 
      in
      helper 0 lst
;;

let rec map f = function
  | [] -> []
  | h::t -> f h :: map f t
;;

let rec powerset = function
   | [] -> [[]]
   | h :: t -> let powset = powerset t in
    powset @ map (fun p -> h :: p) powset
;;
