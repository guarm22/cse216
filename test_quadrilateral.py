from unittest import TestCase
from quadrilateral import Quadrilateral


class TestQuadrilateral(TestCase):

    def test_side_lengths(self):
        floats1 = [2.0, 2.0, 0.0, 2.0, 0.0, 0.0, 2.0, 0.0]
        q1 = Quadrilateral(floats1)
        sides1 = q1.side_lengths()

        floats2 = [3.0, 3.0, 1.0, 3.0, 1.0, 1.0, 3.0, 1.0]
        q2 = Quadrilateral(floats2)
        sides2 = q2.side_lengths()

        floats3 = [1, 0.0, 4.0, 123.0, 543.0, 0.59, 0.0345348, 1.0]
        q3 = Quadrilateral(floats3)
        sides3 = q3.side_lengths()

        # checks if all sides are equal (quadrilateral is square)
        self.assertTrue(all(x == sides1[0] and x == 2.0 for x in sides1))

        iter_num = 0
        for item in sides1:
            self.assertTrue(item == sides2[iter_num])
            iter_num = iter_num + 1



    def test_smallest_x(self):

        floats1 = [2.0, 2.0, 0.0, 2.0, 0.0, 0.0, 2.0, 0.0]
        q1 = Quadrilateral(floats1)

        numbers2 = [2, 2, 0.0, 2.0, 0, 0, 2, 0]
        q2 = Quadrilateral(numbers2)

        self.assertEqual(q1.smallest_x(), 0.0)
        self.assertFalse(q1.smallest_x(), 2.0)
        self.assertEqual(q2.smallest_x(), 0)
        self.assertEqual(q2.smallest_x(), 0.0)

    def test___eq__(self):
        floats2 = [2.0, 2.0, 0.0, 2.0, 0.0, 0.0, 2.0, 0.0]
        q2 = Quadrilateral(floats2)

        floats1 = [2.0, 2.0, 0.0, 2.0, 0.0, 0.0, 2.0, 0.0]
        q1 = Quadrilateral(floats1)

        floats3 = [1.0, 2.0, -1.0, 2.0, -1.0, 0.0, 1.0, 0.0]
        q3 = Quadrilateral(floats3)

        self.assertTrue(q1 == q2)
        self.assertFalse(q2 == q3)

    def test___lt__(self):
        floats2 = [1.0, 2.0, -1.0, 2.0, -1.0, 0.0, 1.0, 0.0]
        q2 = Quadrilateral(floats2)

        floats1 = [2.0, 2.0, 0.0, 2.0, 0.0, 0.0, 2.0, 0.0]
        q1 = Quadrilateral(floats1)

        self.assertTrue(q2 < q1)
        self.assertFalse(q2 > q1)

