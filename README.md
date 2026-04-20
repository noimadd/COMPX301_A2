# Balanced K-way Sort Merge
## 80% Solution

- Damion Sklenars-Clare
- 1638052

## Instructions for use
1. javac *.java

### CopyTest - Testing Only
2. cat MobyDick.txt | java CopyTest > copy.txt
3. diff MobyDick.txt copy.txt

### MakeRuns Usage
2. cat <inputfile>.txt | java MakeRuns -hH > init.runs
- H should be replaced with how ever large you would like the Heap to be

### MergeRuns Usage
3. java MergeRuns -kK > final.txt
- K should be replaced with how many splits you want, although does nothing right now
- Only distribution works currently with init.runs databeing split into 2 separate files

## Repository
https://github.com/noimadd/compx301_A2

Included to demonstrate iterative development