from quadrilateral import Quadrilateral
from two_d_point import TwoDPoint
from square import Square
from rectangle import Rectangle


class ShapeSorter:

    @staticmethod
    def sort(args):
        for shape in args:
            if not isinstance(shape, Quadrilateral):
                raise Exception("Can only sort shapes!")

        return sorted(args, key=lambda quad: Quadrilateral.smallest_x(quad))


if __name__ == "__main__":
    floats1 = [1.0, 2.0, 1.0, 0.0, 3.0, 0.0, 3.0, 2.0]
    floats2 = [0.0, 2.0, 0.0, 0.0, 2.0, 0.0, 2.0, 2.0]
    floats3 = [2.0, 2.0, 2.0, 0.0, 4.0, 0.0, 4.0, 2.0]
    floats4 = [4.0, 2.0, 0.0, 2.0, 0.0, 0.0, 4.0, 0.0]

    q1 = Quadrilateral(floats1)
    q2 = Quadrilateral(floats2)
    q3 = Quadrilateral(floats3)
    q4 = Square(floats1)
    q5 = Rectangle(floats4)
    q6 = Quadrilateral(floats1)
    q7 = Quadrilateral(floats1)

    list1 = [q1, q2, q3, q4, q6, q7]
    tuple1 = (q1, q2, q3, q5)
    list2 = ShapeSorter.sort(list1)
    tuple2 = ShapeSorter.sort(tuple1)
    list3 = ShapeSorter.sort([])
    tuple3 = ShapeSorter.sort(())

    print("Before sorting list: ")
    for item in list1:
        print(item.__str__())
    print()

    print("After sorting list:")
    for item in list2:
        print(item.__str__())
    print()

    print("After sorting tuple")
    for item in tuple2:
        print(item.__str__())