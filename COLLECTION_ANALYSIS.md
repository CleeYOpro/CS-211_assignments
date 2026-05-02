# Collection Analysis for Poker Exercise

This document provides a detailed comparison table of Java collections for the Poker exercise, focusing on the implementation of a deck and a hand of cards. It outlines the pros and cons of each collection type, along with alternative use cases.

| Collection Type | Description                                       | Pros                                                   | Cons                                   | Alternative Use Cases           |
|------------------|---------------------------------------------------|--------------------------------------------------------|---------------------------------------|----------------------------------|
| `ArrayList`      | A resizable array implementation of List.        | - Fast access by index.  
- Good for storing and accessing a collection of cards in order. | - Slow performance for removals/inserts. 
- Requires manual resizing. | - Dynamic lists, maintaining order. |
| `LinkedList`     | A doubly-linked list implementation of List.     | - Fast insertions/removals. 
- Better choice for heavy modifications (add/remove cards). | - Slow access by index. 
- More memory overhead due to node pointers. | - Queues, stacks where frequent add/remove is needed. |
| `HashSet`        | A collection that implements Set.                | - No duplicates allowed. 
- Fast lookups for contains check. | - No order maintained. 
- Not suitable for indexed access. | - Unique items collection (e.g. unique cards). |
| `TreeSet`        | A NavigableSet that is sorted according to its elements. | - Maintains sorted order. 
- Fast lookups, adds/removes when sorted. | - Slower than HashSet for lookups. 
- More overhead due to sorting. | - Need sorted collections (e.g. sorted hand). |
| `Queue`          | A collection used to hold multiple elements prior to processing. | - Good for FIFO order; managing the order of play. | - Limited to FIFO access. 
- Polling can be costly. | - Managing player turns or rounds. |

### Summary

Choosing the right collection type for the Poker exercise depends on the specific requirements, such as speed of access, order dependency, and whether duplicates are allowed. The collection types mentioned above provide distinct advantages and disadvantages that can affect usability in different scenarios.