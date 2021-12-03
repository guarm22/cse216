from two_d_point import TwoDPoint
import math


class Quadrilateral:

    def __init__(self, floats):
        if len(floats) < 8:
            raise Exception("Not enough coordinates to create this shape!")
        for item in floats:
            if not isinstance(item, float) and not isinstance(item, int):
                raise Exception("Invalid coordinates. Must be of type Number")

        points = TwoDPoint.from_coordinates(floats)
        self.__vertices = tuple(points[0:4])
        if self.__is_member():
            raise Exception("Not a valid quadrilateral.")

    def __eq__(self, other):
        if isinstance(other, Quadrilateral):
            x = 0
            for item in self.__vertices:
                if item == other.__vertices[x]:
                    x = x + 1
                else:
                    return False
        else:
            return False
        return True

    def __lt__(self, other):
        if isinstance(other, Quadrilateral):
            return self.smallest_x() < other.smallest_x()

    def __str__(self):
        strg = "Quadrilateral: "
        for item in self.__vertices:
            strg = strg + "(" +item.__str__() + "), "
        return strg

    def __is_member(self):
        list1 = self.__vertices
        bool1 = list1[0] == list1[1] or list1[0] == list1[2] or list1[0] == list1[3] or list1[1] == list1[2] or \
                list1[1] == list1[3] or list1[2] == list1[3]
        return bool1

    @property
    def vertices(self):
        return self.__vertices

    def side_lengths(self):
        sides = [0, 0, 0, 0]
        sides[0] = math.sqrt(((self.__vertices[1].y - self.__vertices[0].y)**2) +
                              ((self.__vertices[1].x - self.__vertices[0].x)**2))

        sides[1] = math.sqrt(((self.__vertices[2].y - self.__vertices[1].y) ** 2) +
                             ((self.__vertices[2].x - self.__vertices[1].x) ** 2))

        sides[2] = math.sqrt(((self.__vertices[3].y - self.__vertices[2].y) ** 2) +
                             ((self.__vertices[3].x - self.__vertices[2].x) ** 2))

        sides[3] = math.sqrt(((self.__vertices[0].y - self.__vertices[3].y) ** 2) +
                             ((self.__vertices[0].x - self.__vertices[3].x) ** 2))

        return sides

    def smallest_x(self):
        x = self.__vertices[0]
        for item in self.__vertices:
            if item.x < x.x:
                x = item

        return x.x
