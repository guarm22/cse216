import math

from two_d_point import TwoDPoint
from rectangle import Rectangle
from quadrilateral import Quadrilateral


class Square(Rectangle):

    def __init__(self, *floats):
        super().__init__(*floats)
        if not self.__is_member():
            raise TypeError("A square cannot be formed by the given coordinates.")

    def __eq__(self, other):
        if isinstance(other, Square):
            x = 0
            for item in self.vertices:
                if item == other.vertices[x]:
                    x = x + 1
                else:
                    return False
        else:
            return False
        return True

    def __str__(self):
        strg = "Square: "
        for item in self.vertices:
            strg = strg + "(" + item.__str__() + "), "
        return strg

    def __is_member(self):
        sides = self.side_lengths()

        squ = (all(x == sides[0] for x in sides))
        return squ

    @staticmethod
    def __round(x):
        if x < 0:
            if x % 0.5 == 0:
                return math.floor(x)
            else:
                return round(x)
        if x > 0:
            if x % 0.5 == 0:
                return math.ceil(x)
            else:
                return round(x)
        return 0

    def snap(self):
        """Snaps the sides of the square such that each corner (x,y) is modified to be a corner (x',y') where x' is the
        integer value closest to x and y' is the integer value closest to y. This, of course, may change the shape to a
        general quadrilateral, hence the return type. The only exception is when the square is positioned in a way where
        this approximation will lead it to vanish into a single point. In that case, a call to snap() will not modify
        this square in any way."""

        rounded_vertices = [0, 0, 0, 0, 0, 0, 0, 0]
        x = 0
        for item in self.vertices:
            rounded_vertices[x] = Square.__round(item.x)
            x = x + 1
            rounded_vertices[x] = Square.__round(item.y)
            x = x + 1

        snapped_vertices = TwoDPoint.from_coordinates(rounded_vertices)

        bool1 = (all(a == snapped_vertices[0] for a in snapped_vertices))
        if bool1:
            return self

        quad = Quadrilateral(rounded_vertices)

        return quad
