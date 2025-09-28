# Assignment 1 – Divide and Conquer Algorithms

## **1. Project Architecture**

The project is organized as follows:

- **algos** – algorithm implementations:
    - `MergeSort` – merge sort with reusable buffer and small-n cutoff (insertion sort)
    - `QuickSort` – randomized pivot, recurse on smaller partition first
    - `DeterministicSelect` – k-th element selection using Median-of-Medians (MoM)
    - `ClosestPair` – 2D closest pair of points

- **core** – utilities and metrics:
    - `Metrics` – tracks comparisons, allocations, recursion depth
    - `Utils` – swap, insertionSort, random array generator

- **cli**
    - `Runner` – runs all algorithms, collects metrics, writes CSV (`metrics.csv`)

- **bench**
    - `SelectBenchmark` – template for JMH benchmarking (optional)

- **tests**
    - JUnit 5 tests for all algorithms and edge cases

---

## **2. Recurrence Analysis**

### **MergeSort**
- Recurrence: \(T(n) = 2T(n/2) + O(n)\)
- Method: Master Theorem, Case 2
- Result: \(\Theta(n \log n)\)
- Metrics match theoretical predictions: recursion depth ≈ log₂ n, comparisons ~ n log n.

### **QuickSort (randomized)**
- Recurrence (average): \(T(n) = T(n/2) + T(n/2) + O(n) \approx 2T(n/2) + O(n)\)
- Method: Master Theorem, Case 2 / intuitive recursion on smaller partition
- Result: \(\Theta(n \log n)\) expected
- Observed: recursion depth bounded ~ 2*log₂ n, comparisons vary depending on pivot randomness.

### **DeterministicSelect**
- Recurrence: \(T(n) = T(n/5) + T(7n/10) + O(n)\)
- Method: Akra–Bazzi / median-of-medians
- Result: \(\Theta(n)\)
- Observed: linear comparisons, recursion depth low, matches theory.

### **Closest Pair (2D)**
- Recurrence: \(T(n) = 2T(n/2) + O(n)\) + strip scan
- Method: Master Theorem, Case 2
- Result: \(\Theta(n \log n)\)
- Observed: recursion depth ~ log₂ n, strip scanning adds small constant factor.

---

## **3. Plots and Metrics**

CSV `metrics.csv` contains:

| Algorithm | n   | Time_ms | Depth | Comparisons | Allocations |
|-----------|-----|---------|-------|-------------|-------------|

Example plots (generated externally):

- **Time vs n** – shows n log n growth for MergeSort, QuickSort, ClosestPair; linear for Select
- **Recursion Depth vs n** – logarithmic for all divide-and-conquer algorithms
- **Comparisons/Allocations** – reflects constant factor differences, buffer reuse, and insertion sort cutoff

> Note: constants affected by cache, GC, and Java runtime optimizations.

---

## **4. Summary / Observations**

- All algorithms are correct for random and adversarial inputs.
- Measured metrics align closely with theoretical predictions.
- Small-n cutoff and reusable buffers improve MergeSort performance.
- QuickSort recursion on smaller partition keeps stack bounded.
- DeterministicSelect works in linear time as expected.
- ClosestPair in 2D achieves O(n log n) with strip optimization.

---

## **5. How to Run**

```bash
# Run Runner to execute all algorithms and generate metrics.csv
mvn compile exec:java -Dexec.mainClass="com.assignment1.cli.Runner"
