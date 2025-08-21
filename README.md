# 📘 Book-Scrabble System

A comprehensive Scrabble game in Java, incorporating advanced software engineering principles and design patterns.  
The project features:

---

## 🚀 Main Features

### 🔤 Tile Management
Simulates a tile management system with:
- Immutable tiles for consistency
- Design patterns for managing the tile repository (Singleton)
- Algorithms for random and specific tile retrieval
- Mechanism to return tiles to the bag

### 🖧 Server Management
- Ensures server stability
- Manages client connections effectively

### 📚 Dictionary Integration
- Utilizes a **Bloom Filter** for efficient real-time word validation
- Uses a `CacheManager` for optimized data access

### 🧩 Software Engineering Practices
- Demonstrates modular OOP design
- Encapsulates file reading operations
- Implements Strategy design pattern for cache replacement policies

This project showcases skills in scalable architecture, data handling, and modern software design practices.

---

## 🧠 Tile Management (Detailed)

This part simulates a tile management system for a Scrabble-like word game, showcasing various software engineering principles and practices.

**Features:**
- **Immutable Tiles**: Ensures tile states remain consistent throughout the game.
- **Singleton Bag**: Manages the central tile repository with global access and prevents multiple instantiations.
- **Random and Specific Tile Retrieval**: Uses algorithms to manage and manipulate data structures effectively.
- **Tile Return Mechanism**: Supports returning tiles to the bag.

---

## 🧪 Class Overviews

### 🔹 BloomFilter

**Description**:  
Manages a space-efficient probabilistic data structure used to test element membership.

**Attributes**:
- `filterBits`: A BitSet representing the filter
- `hashEngines`: An array of `MessageDigest` instances (hash functions)

**Methods**:
- `BloomFilter(...)`: Constructor to initialize filter size and hash algorithms  
- `add(String element)`: Adds an element  
- `contains(String element)`: Checks if an element might exist  
- `computeIndex(...)`: Computes the index in the bitset  
- `toString()`: Debugging representation

---

### 🔹 CacheManager

**Description**:  
Stores frequently accessed data to reduce repeated expensive operations.

**Attributes**:
- `cache`: Set of cached items  
- `maxSize`: Max number of items  
- `replacementPolicy`: Strategy for cache eviction

**Methods**:
- `CacheManager(...)`: Constructor  
- `query(String word)`  
- `add(String word)`  
- `remove(String word)`

---

### 🔹 Dictionary

**Description**:  
Manages word lookups using caching and probabilistic validation.

**Attributes**:
- `existingWordsCache`: CacheManager for valid words  
- `nonExistingWordsCache`: CacheManager for invalid words  
- `bloomFilter`: Probabilistic word presence checker  
- `fileNames`: File names to load

**Methods**:
- `Dictionary(...)`: Constructor  
- `query(String word)`  
- `challenge(String word)`

---

### 🔹 IOSearcher

**Description**:  
Performs direct file I/O for word lookup.

**Methods**:
- `search(String word, String... fileNames)`: Returns `true` if word is found

---

### 🔹 CacheReplacementPolicy (Interface)

**Methods**:
- `add(String word)`  
- `remove()`

**Implementations**:
- **LRU** (Least Recently Used): Removes the least recently used item  
- **LFU** (Least Frequently Used): Removes the least frequently used item

---

## 📐 Software Engineering Elements & Best Practices

- **Design and Architecture Templates**: Uses the Singleton pattern to manage state  
- **Strategy Pattern**: To allow flexible cache replacement  
- **Server-Client Communication**: While this version focuses on logic, the code is structured for future multiplayer support via a server-client model.
