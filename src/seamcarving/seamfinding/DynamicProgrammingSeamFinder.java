package seamcarving.seamfinding;


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
        double[][] pixels = new double[picture.width()][picture.height()];
        double pixelval = 0;
        double leftup = 0;
        double leftmiddle = 0;
        double leftdown = 0;

        for (int i = 0; i < picture.width(); i++) {
            pixels[0][i] = f.apply(picture, 0, i);
        }
        for (int col = 1; col < picture.width(); col++) {
            for (int row = 0; row < picture.height(); row++) {
                pixelval = f.apply(picture, row, col);
                if (row == 0) { //top of image only has two neighbors
                    leftmiddle = pixels[row][col - 1];
                    leftdown = pixels[row - 1][col - 1];
                    pixels[row][col] = Math.min(leftmiddle, leftdown) + pixelval;
                } else if (row == picture.height() - 1) { //bottom of image only has two neighbors
                    leftup = pixels[row + 1][col - 1];
                    leftmiddle = pixels[row][col - 1];
                    pixels[row][col] = Math.min(leftup, leftmiddle) + pixelval;
                } else {
                    leftup = pixels[row + 1][col - 1];
                    leftmiddle = pixels[row][col - 1];
                    leftdown = pixels[row - 1][col - 1];
                    pixels[row][col] = Math.min(leftup, Math.min(leftmiddle, leftdown)) + pixelval;
                }
            }
        }
        List<Double> columnvalues = new ArrayList();
        double minval = Double.POSITIVE_INFINITY;
        int minindex=0;
        for (int row = 0; row < picture.height(); row++) {
            if((pixels[row][picture.width()-1]) < minval) {
                minval = pixels[row][picture.width()-1];
                minindex = row;
            }
        }
        shortestPath.add(minindex);
        //double minval = Double.POSITIVE_INFINITY;
        //int minindex=0;
        for (int col = picture.width()-2; col >= 0; col--) {

            columnvalues.add(pixels[row][col]);
            shortestPath.add(Collections.min(columnvalues));
        }
        return shortestPath;
    }

}
