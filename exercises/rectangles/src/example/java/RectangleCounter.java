import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.stream;

final class RectangleCounter {

    int countRectangles(final String[] rawGrid) {
        final int nRows = rawGrid.length;
        if (nRows == 0) return 0;

        final int nCols = rawGrid[0].length();
        if (nCols == 0) return 0;

        return new Grid(nRows, nCols, rawGrid).countRectangles();
    }

    private static final class Grid {

        private enum Element {
            CORNER,
            HLINE,
            VLINE,
            SPACE;

            private static Element fromChar(final char rawGridEntry) {
                switch (rawGridEntry) {
                    case '+': return CORNER;
                    case '-': return HLINE;
                    case '|': return VLINE;
                    case ' ': return SPACE;
                    default:  throw new IllegalStateException("Grid entry " + rawGridEntry + " not recognized.");
                }
            }

            private boolean isHorizontalConnector() {
                return this == CORNER || this == HLINE;
            }

            private boolean isVerticalConnector() {
                return this == CORNER || this == VLINE;
            }
        }

        private int nRows, nCols;
        private final Element[][] entries;

        private Grid(final int nRows, final int nCols, final String[] rawGrid) {
            this.nRows = nRows;
            this.nCols = nCols;
            this.entries = new Element[nRows][nCols];

            for (int nRow = 0; nRow < nRows; nRow++) {
                for (int nCol = 0; nCol < nCols; nCol++) {
                    entries[nRow][nCol] = Element.fromChar(rawGrid[nRow].charAt(nCol));
                }
            }
        }

        private int countRectangles() {
            int result = 0;

            for (int topRow = 0; topRow < nRows - 1; topRow++) {

                for (int leftCol = 0; leftCol < nCols - 1; leftCol++) {

                    // Only check rectangles that lie below/to the right of our current coordinate:
                    for (int bottomRow = topRow + 1; bottomRow < nRows; bottomRow++) {

                        for (int rightCol = leftCol + 1; rightCol < nCols; rightCol++) {

                            if (formsRectangle(topRow, bottomRow, leftCol, rightCol)) {
                                result++;
                            }
                        }
                    }
                }
            }

            return result;
        }

        private boolean formsRectangle(
                final int topRow,
                final int bottomRow,
                final int leftCol,
                final int rightCol) {

            return entries[topRow][leftCol].equals(Element.CORNER)
                    && entries[topRow][rightCol].equals(Element.CORNER)
                    && entries[bottomRow][leftCol].equals(Element.CORNER)
                    && entries[bottomRow][rightCol].equals(Element.CORNER)
                    && isHorizontalLineSegment(topRow, leftCol, rightCol)
                    && isHorizontalLineSegment(bottomRow, leftCol, rightCol)
                    && isVerticalLineSegment(leftCol, topRow, bottomRow)
                    && isVerticalLineSegment(rightCol, topRow, bottomRow);
        }

        private boolean isHorizontalLineSegment(final int row, final int leftCol, final int rightCol) {
            return stream(copyOfRange(getRow(row), leftCol, rightCol))
                    .allMatch(Element::isHorizontalConnector);
        }

        private boolean isVerticalLineSegment(final int col, final int topRow, final int bottomRow) {
            return stream(copyOfRange(getCol(col), topRow, bottomRow))
                    .allMatch(Element::isVerticalConnector);
        }

        private Element[] getRow(final int number) {
            return entries[number];
        }

        private Element[] getCol(final int number) {
            final Element[] result = new Element[nRows];

            for (int nRow = 0; nRow < nRows; nRow++) {
                result[nRow] = entries[nRow][number];
            }

            return result;
        }

    }

}
