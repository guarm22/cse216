from typing import List


class TwoDPoint:

    def __init__(self, x, y) -> None:

        if not isinstance(x, int) and not isinstance(x, float):
            raise Exception("Invalid type for creating TwoDPoint.")

        if not isinstance(y, int) and not isinstance(y, float):
            raise Exception("Invalid type for creating TwoDPoint")

        self.__x = x
        self.__y = y


    @property
    def x(self):
        return self.__x

    @property
    def y(self):
        return self.__y

    def __eq__(self, other: object) -> bool:
        if isinstance(other, TwoDPoint):
            return other.x == self.x and other.y == self.y
        else:
            return False

    def __ne__(self, other: object) -> bool:
        return not self.__eq__(other)

    def __str__(self) -> str:
        return '(%g, %g)' % (self.__x, self.__y)

    def __add__(self, other):
        if isinstance(other, TwoDPoint):
            return TwoDPoint(self.x+other.x, self.y+other.y)
        else:
            raise Exception("Cannot add two different types")

    def __sub__(self, other):
        if isinstance(other, TwoDPoint):
            return TwoDPoint(self.x-other.x, self.y-other.y)
        else:
            raise Exception("Cannot subtract two different types")

    @staticmethod
    def from_coordinates(coordinates: List[float]):
        if len(coordinates) % 2 == 1:
            raise Exception("Odd number of floats given to build a list of 2-d points")
        points = []
        it = iter(coordinates)
        for x in it:
            points.append(TwoDPoint(x, next(it)))
        return points
