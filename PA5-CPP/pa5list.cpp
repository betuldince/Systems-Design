// Name:Betul Dincer
// USC NetID:1816821532
// CS 455 PA5
// Fall 2023

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {
   
   ListType list = NULL ;
 
   append(list, "S", 30);
   append(list, "B", 30);
   append(list, "KL", 60);
   append(list, "B", 0);
   change(list,"B",33) ;
   removeList(list,"Kkk");
   /*
   int *k = lookUpList(list, "Betul");
   if(k!=NULL){
      cout << *k <<endl;
   }
   */
   cout<< "Num entries: " << numEntriesList(list) << endl;
   
   printList(list);


   return 0;
}
