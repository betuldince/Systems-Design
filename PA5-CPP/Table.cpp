// Name: Betul Dincer
// USC NetID:1816821532
// CSCI 455 PA5
// Fall 2023

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

// for hash function called in private hashCode method defined below
#include <functional>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {  
   hashSize = HASH_SIZE;  
   data = new ListType[hashSize]();
}


Table::Table(unsigned int hSize) {  
   hashSize = hSize;
   data = new ListType[hSize]();
}


int * Table::lookup(const string &key) {
   int code = hashCode(key);   
   return lookUpList(data[code],key);    
}


bool Table::remove(const string &key) {
   int code = hashCode(key);
   return removeList(data[code], key);  
}


bool Table::insert(const string &key, int value) {
   int code = hashCode(key);
   // if the data doesn't exist, insert it.
   if(!lookup(key)){
      append(data[code], key, value);
      return true;
   }
   // if the data exist and we are not performing inserting, change it to the new value
   if(lookup(key) && ! append(data[code], key, value)){
      change(data[code], key, value);
      return true;
   }
   return false;
}


int Table::numEntries() const {
  int counter = 0;
  for(int i=0;i<hashSize;i++){
     Node * p = data[i];
     if(p != NULL){
        counter += numEntriesList(p);    
     }    
   }
   return counter;       
}


void Table::printAll() const {
   for(int i=0;i<hashSize;i++){
      Node * p = data[i];
      if(p != NULL){
         printList(p);         
      }      
   }
}


void Table::hashStats(ostream &out) const {
   out<< "number of buckets: " << hashSize << endl;
   out<< "number of entries: " << numEntries() << endl;
   out<< "number of non-empty buckets: " << nonEmptyBucket() << endl;
   out<< "longest chain: " << longestChain() << endl;
}


// hash function for a string
// (we defined it for you)
// returns a value in the range [0, hashSize)
unsigned int Table::hashCode(const string &word) const {

   // Note: calls a std library hash function for string (it uses the good hash
   //   algorithm for strings that we discussed in lecture).
   return hash<string>()(word) % hashSize;  

}


// add definitions for your private methods here

int Table::nonEmptyBucket() const {
   int counter = 0;
   for(int i=0;i<hashSize;i++){
      Node * p = data[i];
      if(p != NULL){
         counter += 1;         
      }      
   }
   return counter;   
}

int Table::longestChain() const {
   int max = 0;
   for(int i=0;i<hashSize;i++){
      Node * p = data[i];
      if(p != NULL){
         if(numEntriesList(p)>max){
            max = numEntriesList(p);
         }        
      }      
   }
   return max;
}
 