package seamcarving.seamfinding;


import graphs.Edge;
import seamcarving.Picture;
import seamcarving.SeamCarver;
import seamcarving.energy.EnergyFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 * @see SeamCarver
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {

    @Override
    public List<Integer> findHorizontal(Picture picture, EnergyFunction f) {
        // TODO: Replace with your code
        List<Integer> shortestPath = new ArrayList<>();
        double[][] pixels = new double[picture.height()][picture.width()];
        double pixelval = 0;
        double leftup = 0;
        double leftmiddle = 0;
        double leftdown = 0;

        //initialize the 2D array by getting first column because they only depend on their energy
        for (int i = 0; i < picture.height(); i++) {
            pixels[i][0] = f.apply(picture, 0, i);
        }
        //loop through all columns to the right, and fill in the 2D array with energy values based on its minimum neighbor
        for (int col = 1; col < picture.width(); col++) {
            for (int row = 0; row < picture.height(); row++) {
                pixelval = f.apply(picture, col, row);
                if (row == 0) { //top of image only has two neighbors
                    leftmiddle = pixels[row][col - 1];
                    leftdown = pixels[row + 1][col - 1];
                    pixels[row][col] = Math.min(leftmiddle, leftdown) + pixelval;
                } else if (row == picture.height() - 1) { //bottom of image only has two neighbors
                    leftup = pixels[row - 1][col - 1];
                    leftmiddle = pixels[row][col - 1];
                    pixels[row][col] = Math.min(leftup, leftmiddle) + pixelval;
                } else { //everything that was 3 neighbors
                    leftup = pixels[row - 1][col - 1];
                    leftmiddle = pixels[row][col - 1];
                    leftdown = pixels[row + 1][col - 1];
                    pixels[row][col] = Math.min(leftup, Math.min(leftmiddle, leftdown)) + pixelval;
                }
            }
        }
        List<Double> columnvalues = new ArrayList();
        double minval = Double.POSITIVE_INFINITY;
        int minindex=0;
        //initialize search by finding the minimum value in the last column
        for (int row = 0; row < picture.height(); row++) {
            if((pixels[row][picture.width()-1]) < minval) {
                minval = pixels[row][picture.width()-1];
                minindex = row;
            }
        }
        //backtrack through 2D array to find shortest path
        shortestPath.add(minindex); //first entry on the path with by the min of last column
        //double minval = Double.POSITIVE_INFINITY;
        int y=minindex;
        for (int col = picture.width()-2; col >= 0; col--) {
            minval = Double.POSITIVE_INFINITY;
            y=minindex;
            for (int row = y - 1; row <= y + 1; row += 1) {
                // Only if the neighbor is in the bounds of the picture.
                if (0 <= row && row < picture.height()) {
                    if ((pixels[row][col]) < minval){
                        minindex = row;
                        minval = pixels[row][col];
                    }
                }
            }
            shortestPath.add(minindex);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
}
