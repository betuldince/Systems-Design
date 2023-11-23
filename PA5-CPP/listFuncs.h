// Name:Betul Dincer
// USC NetID:1816821532
// CSCI 455 PA5
// Fall 2023


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Node class
//it represents individual elements within the list. Node contains key and value for information storing and it has next pointer for creating the link between the nodes. It is used in Table class for creating chains in the buckets.  


// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to *not* put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

//insert a new node to the list.
//return false if the list already contains the key and no insert made to the list. If the insert is made successfully, return true.
//PRE: theKey should not contain whitespaces. theValue should be greater than 0.
bool append (ListType &list,const std::string &theKey, int theValue);

//change the value of the node.
//return false if the list does not contain the key and no change made to the list. If the change is made successfully, return true.
//PRE: theKey should not contain whitespaces. theValue should be greater than 0.
bool change(ListType &list,const std::string &theKey, int theValue);

//print the keys and the values in the chain.
void printList(const ListType &list);

//remove the node with given key. 
//return false, if the removing is unsuccessful. return true, if the removing is made.
//PRE: theKey should not contain whitespaces.
bool removeList(ListType &list, const std::string &theKey);

// returns the address of the node that goes with this key
// or NULL if key is not present.
// PRE: key shouldn't contain any whitespace, 
int* lookUpList(ListType &list, const std::string &theKey);

// returns the number of entries in the chain.
int numEntriesList(const ListType &list);

// keep the following line at the end of the file
#endif
