from unittest import TestCase
from two_d_point import TwoDPoint


class TestTwoDPoint(TestCase):

    def test_from_coordinates(self):
        floats1 = [2, 2.0, 0.0, 0.0, 2.0, 0.0, 2.0, 2.0]
        points = TwoDPoint.from_coordinates(floats1)
        self.assertTrue(len(points) == 4)
        self.assertTrue(points[1] == TwoDPoint(0.0, 0.0))

    def test___eq__(self):
        td1 = TwoDPoint(2.0, 2.0)
        td2 = TwoDPoint(2.0, 2.0)
        td3 = TwoDPoint(3.0, 3.0)
        self.assertTrue(td1 == td2)
        self.assertFalse(td1 == td3)

    def test___add__(self):
        td1 = TwoDPoint(2.0, 2.0)
        td2 = TwoDPoint(2.0, 2.0)
        td3 = td1+td2
        self.assertTrue(td3 == TwoDPoint(4.0, 4.0))
        self.assertFalse(td1 + td3 == TwoDPoint(10.0, 10.0))

    def test___sub__(self):
        td1 = TwoDPoint(2.0, 2.0)
        td2 = TwoDPoint(2.0, 2.0)
        td3 = td1-td2
        self.assertTrue(td3 == TwoDPoint(0.0, 0.0))
        self.assertFalse(td1 + td3 == TwoDPoint(10.0, 10.0))
