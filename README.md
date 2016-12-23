# sudoku
Standard 9x9 sudoku solving and generation program.

## about

Basic object-oriented approach to sudoku generation and solving using a backtracking algorithm and random number selection to achieve the end goal.

## usage

- `-s, -solve`
  - Input 9 lines of 9 digits representing your sudoku. Use 0 for blank spaces.
- `-g, -generate`
  - Generates a random, beginner-to-intermediate level sudoku.
- `-h, -help`
  - Prints help.

## implemented
- Basic program to input sudoku
- Backtracking approach to solve 9x9 sudoku
- Random sudoku generation

## roadmap

### general
- Input validation on cli-entered sudoku
- Pipe support for STDIN and STDOUT and/or to file
- `argparse4j` dependency
- 6x6 sudoku support
- GUI
  - How best to approach this? I usually do a "-gui" flag in the command-line and have a shortcut, but might just be better to recompile a `.jar` with a separate, GUI-entric entry point.
- OCR for scanned-in/screenshots of sudokus?

### generation
- Levels of difficulty
- Pattern-based generation (list of patterns to mask the generated, solved sudoku)

## license
The MIT License (MIT)

Copyright (c) 2015 Andrew Lamzed-Short

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.