package a01;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
/**
 * GridTest.java is a file that tests the functionality of several methods in the Grid class.
 * Methods tested include largestNeighbor, Snake1, and Spiral1. Each method has 4 different tests run on it.
 * @author Malcolm Bailey
 *
 */
public class GridTest {
		
	
	@Test
	public void testlargestNeighborFalse() {
		
		int[][] grid =  {{1,2,3}, {4,5,6}, {7,8,9}};
		Grid testGrid = new Grid(grid);
		Assert.assertFalse(testGrid.largestNeighbor(1,1));
	}
	
	@Test
	public void testlargestNeighborTrue() {
		
		int[][] grid =  {{1,2,3,4}, {4,10,6,8}, {7,8,9,2}, {2, 4, 8, 1}};
		Grid testGrid = new Grid(grid);
		Assert.assertTrue(testGrid.largestNeighbor(1,1));
	}
	
	@Test
	public void testlargestNeighborFalse2() {
		
		int[][] grid =  {{1,20}, {4,10}};
		Grid testGrid = new Grid(grid);
		Assert.assertFalse(testGrid.largestNeighbor(1,1));
	}
	
	@Test
	public void testlargestNeighborTrue2() {
		
		int[][] grid =  {{1}};
		Grid testGrid = new Grid(grid);
		Assert.assertTrue(testGrid.largestNeighbor(0,0));
	}

	
	
	@Test
	public void testSnake1One() {
		
		int[][] grid =  {{1,20,3}, {4,10,6}, {7,8,90}};
		Grid testGrid = new Grid(grid);
		int[] arr = {1, 20, 3, 6, 10, 4, 7, 8, 90};
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (Integer num : arr) {
			expected.add(num);
		}
		
		Assert.assertEquals(expected, testGrid.snake1());
	}
	
	@Test
	public void testSnake1Two() {
		
		int[][] grid =  {{1}};
		Grid testGrid = new Grid(grid);
		int[] arr = {1};
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (Integer num : arr) {
			expected.add(num);
		}
		
		Assert.assertEquals(expected, testGrid.snake1());
	
	}
	
	@Test
	public void testSnake1Three() {
		
		int[][] grid =  {{1,2,3,4}, {4,10,6,8}, {7,8,9,2}, {2, 4, 8, 1}};
		Grid testGrid = new Grid(grid);
		int[] arr = {1,2,3,4,8,6,10,4,7,8,9,2,1,8,4,2};
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (Integer num : arr) {
			expected.add(num);
		}
		
		Assert.assertEquals(expected, testGrid.snake1());
	}
	
	@Test
	public void testSnake1Four() {
		
		int[][] grid =  {{1,20}, {4,10}};
		Grid testGrid = new Grid(grid);
		int[] arr = {1, 20, 10, 4};
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (Integer num : arr) {
			expected.add(num);
		}
		
		Assert.assertEquals(expected, testGrid.snake1());
	}
	
	
	
	@Test
	public void testSpiral1One() {
		
		int[][] grid =  {{1,20,3}, {4,10,6}, {7,8,90}};
		Grid testGrid = new Grid(grid);
		int[] arr = {1, 20, 3, 6, 90, 8, 7, 4, 10};
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (Integer num : arr) {
			expected.add(num);
		}
		
		Assert.assertEquals(expected, testGrid.spiral1());
	}
	
	@Test
	public void testSpiral1Two() {

		int[][] grid =  {{1}};
		Grid testGrid = new Grid(grid);
		int[] arr = {1};
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (Integer num : arr) {
			expected.add(num);
		}
		
		Assert.assertEquals(expected, testGrid.spiral1());
	}
	
	@Test
	public void testSpiral1Three() {
		
		int[][] grid =  {{1,2,3,4}, {4,10,6,8}, {7,8,9,2}, {2, 4, 8, 1}};
		Grid testGrid = new Grid(grid);
		int[] arr = {1,2,3,4,8, 2, 1, 8, 4, 2, 7, 4, 10, 6, 9, 8};
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (Integer num : arr) {
			expected.add(num);
		}
		
		Assert.assertEquals(expected, testGrid.spiral1());
	}
	
	@Test
	public void testSpiral1Four() {
		
		int[][] grid =  {{1,20}, {4,10}};
		Grid testGrid = new Grid(grid);
		int[] arr = {1, 20, 10, 4};
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (Integer num : arr) {
			expected.add(num);
		}
		
		Assert.assertEquals(expected, testGrid.spiral1());

	}
}
