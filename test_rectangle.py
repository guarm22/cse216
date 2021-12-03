from unittest import TestCase
from rectangle import Rectangle
from two_d_point import TwoDPoint


class TestRectangle(TestCase):

    def test_center(self):
        floats1 = [4.0, 2.0, 2.0, 2.0, 0.0, 0.0, 4.0, 0.0]
        rec1 = Rectangle(floats1)
        self.assertTrue(rec1.center() == TwoDPoint(2, 1))

    def test_area(self):
        floats1 = [4.0, 2.0, 0.0, 2.0, 0.0, 0.0, 4.0, 0.0]
        rec1 = Rectangle(floats1)
        self.assertTrue(rec1.area() == 8)

    def test___eq__(self):

        floats1 = [4.0, 2.0, 0.0, 2.0, 0.0, 0.0, 4.0, 0.0]
        rec1 = Rectangle(floats1)
        floats2 = [4.0, 2.0, 0.0, 2.0, 0.0, 0.0, 4.0, 0.0]
        rec2 = Rectangle(floats2)
        floats3 = [3.0, 2.0, -1.0, 2.0, -1.0, 0.0, 3.0, 0.0]
        rec3 = Rectangle(floats3)

        self.assertTrue(rec1 == rec2)
        self.assertFalse(rec2 == rec3)
