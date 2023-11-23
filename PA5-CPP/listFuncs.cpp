// Name:Betul Dincer
// USC NetID:1816821532
// CSCI 455 PA5
// Fall 2023

// listFuncs.cpp  Node class implementation

#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

 
bool append (ListType &list,const std::string &theKey, int theValue){
   Node *p = list;
   if(p == NULL){
      ListType nG = new Node(theKey, theValue,list);
      list = nG;   
      return true;
   }
   int *l = lookUpList(list, theKey);
   // append only if the data doesn't exist
   if(l==NULL){     
      while(p->next != NULL){
         p = p->next;
      }
      p->next = new Node(theKey, theValue);
      return true;
   }
   return false;
}

bool change(ListType &list,const std::string &theKey, int theValue){      
   int *l = lookUpList(list, theKey);
   if(l==NULL){
      return false;
   // if data is present in the linkedlist, change the value   
   }else{
      Node *p = list;
      while(p->next != NULL && p->key!=theKey){
         p = p->next;
      }
      if(p->key == theKey){
         p->value = theValue;
      }
      return true;
   }  
}

void printList(const ListType & list){
   Node * p = list;
   while(p != NULL){
      std::cout << p -> key << " "<< p -> value<< " ";
      p = p -> next;
   }
   std:: cout << std::endl;
}

bool removeList(ListType &list, const std::string &theKey){
   // NULL
   if(list == NULL){
      return false;
   }
   // 1 element, first one has the key
   if(list->next == NULL && list->key == theKey){
      delete list;
      list = NULL;
      return true;
   // 1 element, it doesn't have the Key 
   } else if(list->next == NULL && list->key != theKey){
      return false;
   // More than 1 element, first one has the Key    
   }else if(list->key == theKey){
      Node * dump = list;
      list = list->next;
      delete dump;
      return true;
   }
   Node *p = list;  
   while(p->next->next!=NULL && p->next->key != theKey){       
      p = p->next;     
   }
   // The last one has the Key
   if(p->next->next == NULL && p->next->key == theKey){
      Node * dump1 = p->next;
      p->next = NULL;
      delete dump1;
      return true;
   }
   // The middle one has the Key
   if(p->next->key == theKey){
      Node *dump3 = p->next;
      p->next = p->next->next;
      delete dump3;
      return true;
   }
   //Not found
   return false;
}

int* lookUpList(ListType &list, const std::string &theKey){
   if(list == NULL){
      return NULL;
   }
   Node *p = list;
   while(p->next!=NULL && p->key != theKey){
      p = p->next;
   }
   if (p->key == theKey){
      return &p->value;
   }   
   return NULL;
}


int numEntriesList(const ListType & list){
   int counter = 1;
   Node * p= list;
   if(p == NULL){
      return 0;
   }
   while(p->next!=NULL){
      p = p->next;
      counter +=1;
   }
   return counter;
}




















