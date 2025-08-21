# 📘 Book-Scrabble System

A complete object-oriented Java implementation of a Scrabble-inspired game engine.  
This project was built as part of a university-level **Software Engineering course**, with a strong focus on modularity, performance, and design patterns.

> 🧠 **Note:** This version is purely logical and does not include a graphical interface. The game is run and tested via the console.

---

## 🚀 Features

### 🔤 Tile Management

- Immutable `Tile` objects ensure consistency  
- Singleton `Bag` class manages all tiles and supports:
  - Random tile retrieval  
  - Specific tile retrieval  
  - Returning tiles to the bag  

### 🧠 Word Verification

- Uses a **Bloom Filter** to efficiently validate if a word exists  
- Words are checked against files (books) passed to the system  
- `Dictionary` class:
  - Caches valid and invalid queries using an internal `CacheManager`
  - Uses `BloomFilter` for probabilistic lookup
  - Falls back to full file search when necessary

### 💾 Cache System

- Custom `CacheManager` implementation  
- Supports two cache replacement policies via the **Strategy pattern**:
  - LRU (Least Recently Used)  
  - LFU (Least Frequently Used)  

### 📁 File Searching

- `IOSearcher` class performs efficient word searches across multiple book files  
- Used as a fallback in `Dictionary.challenge`  

### 🖧 Server-Client Architecture

- `MyServer` handles single-threaded client connections  
- `BookScrabbleHandler` interprets queries in the format:
  - `"Q,book1.txt,book2.txt,...,word"` (query)
  - `"C,book1.txt,...,word"` (challenge)  
- Supports validation and communication in a simulated multiplayer scenario

---

## 🧪 Sample Use Case

The main class `MainTrain` contains unit-style tests that:

- Simulate real Scrabble-like moves  
- Validate presence of words  
- Show cache behavior and Bloom filter efficiency  

### 🏃 To run:

```bash
javac -d . */*.java
java test.MainTrain
