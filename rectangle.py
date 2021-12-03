from quadrilateral import Quadrilateral
from two_d_point import TwoDPoint


class Rectangle(Quadrilateral):

    def __init__(self, floats):
        super().__init__(floats)
        if not self.__is_member():
            raise TypeError("A rectangle cannot be formed by the given coordinates.")

    def __eq__(self, other):
        if isinstance(other, Rectangle):
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
        strg = "Rectangle: "
        for item in self.vertices:
            strg = strg + "(" + item.__str__() + "), "
        return strg

    def __is_member(self):
        """Returns True if the given coordinates form a valid rectangle, and False otherwise."""
        sides = self.side_lengths()
        rec = len(self.vertices) == 4 and sides[0] == sides[2] and sides[1] == sides[3]
        return rec

    def center(self):
        """Returns the center of this rectangle, calculated to be the point of intersection of its diagonals."""

        cen_x = (self.vertices[3].x + self.vertices[1].x) / 2
        cen_y = (self.vertices[3].y + self.vertices[1].y) / 2

        return TwoDPoint(cen_x, cen_y)

    def area(self):
        """Returns the area of this rectangle. The implementation invokes the side_lengths() method from the superclass,
        and computes the product of this rectangle's length and width."""

        sides = self.side_lengths()
        return sides[0] * sides[1]
