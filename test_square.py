from unittest import TestCase
from square import Square
from quadrilateral import Quadrilateral


class TestSquare(TestCase):

    def test_snap(self):
        floats1 = [0.49, 0.49, 0.0, 0.49, 0.0, 0.0, 0.49, 0.0]
        squ1 = Square(floats1)
        # should return true because all the points will snap to 0.0 and then return the original square
        self.assertTrue(squ1.snap() == squ1)

        floats2 = [0.51, 0.51, 0.0, 0.51, 0.0, 0.0, 0.51, 0.0]
        squ2 = Square(floats2)
        # should return true because all the points at 0.51 will snap to 1.0, keeping the square shape
        self.assertFalse(squ2.snap() == squ2)

        floats3 = [.5, .5, .0, .5, .0, .0, .5, .0]
        floats4 = [1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0]
        squ3 = Square(floats3)
        q1 = Quadrilateral(floats4)
        self.assertEqual(squ3.snap(), q1)

    def test___eq__(self):
        floats1 = [1, 1, 0, 1, 0, 0, 1, 0]
        squ1 = Square(floats1)
        floats2 = [1, 1, 0, 1, 0, 0, 1, 0]
        squ2 = Square(floats2)
        floats3 = [3, 3, 0, 3, 0, 0, 3, 0]
        squ3 = Square(floats3)

        self.assertTrue(squ1 == squ2)
        self.assertFalse(squ1 == squ3)

