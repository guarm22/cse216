class Person(object):
	def __init__(self, name, idnumber):
		self.name = name
		self.idnumber = idnumber
class Employee(object):
	def __init__(self, salary, post):
		self.salary = salary
		self.post = post
class Leader(Person, Employee):
	def __init__(self, name, idnumber, salary, post, points):
		self.points = points
		Person.__init__(self, name, idnumber)
		Employee.__init__(self, salary, post)
		print(self.salary)


object = Leader("Jacob", 75000 , 882016,"Assistant Manager", 560)