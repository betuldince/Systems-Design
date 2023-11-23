// Name:Betul Dincer
// USC NetID:1816821532
// CSCI 455 PA5
// Fall 2023

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

void inputSystem(Table * grades);
void insertName(Table * grades, const string name, const int score );
void changeName(Table * grades, const string name, const int score );
void lookUpName(Table * grades, const string name);
void removeName(Table * grades, const string name);
void invalid();

int main(int argc, char * argv[]) {

   Table * grades;  // Table is dynamically allocated below, so we can call
                     // different constructors depending on input from the user.
   
   // optionally gets the hash table size from the command line
   if (argc > 1) {
      int hashSize = atoi(argv[1]);  // atoi converts c-string to int
      
      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }
   grades->hashStats(cout);
   // add more code here
   inputSystem(grades);
   // Reminder: use -> when calling Table methods, since grades is type Table*
   return 0;
}

// This function takes the command line inputs from the user and perform the specific tasks according to the taken inputs.
//PRE: grades should be initialized beforehand. 
void inputSystem(Table * grades){
   string name="";
   int score = 0;
   string cmd;
   bool finish = false;   
   do{
      cout << "cmd> ";
      cin >> cmd;
      if(cmd == "insert" ) {
         cin >> name >> score;
         insertName(grades, name,score);
      }else if(cmd == "change"){
         cin >> name >> score;
         changeName(grades, name,score); 
      }else if (cmd == "lookup") {
         cin >> name;
         lookUpName(grades,name);
      }else if (cmd == "remove") {
         cin >> name;
         removeName(grades,name);
      }else if (cmd == "print") {
         grades->printAll();
      }else if (cmd == "size") {
         cout << "The number of students: " << grades->numEntries() << endl;
      }else if (cmd == "stats") {
         grades->hashStats(std::cout);
      }else if (cmd == "help") {
         cout << "Please enter a command [insert, change, lookup, remove, print, size, stats, help, quit]" <<endl;
      }else if (cmd == "quit") {
         finish = true;
      }else {
         invalid();
      }    
   }while(!finish);  
}

// This fuction inserts the given name and score pair to the table.
// To be inserted, the name should not be exist in the table.
// PRE: the name should not contain whitespace, score must be greater than 0.
void insertName(Table * grades, const string name, const int score ){
   if (grades -> lookup(name)|| !grades->insert(name,score)) {
      cout << "Student already exists. Not inserting." << endl;
   } else {
      grades->insert(name, score);                
   }   
}
// This fuction changes the given name with the given score.
// To be changed, the name should be exist in the table and the core should be different.
// PRE: the name should not contain whitespace, score must be greater than 0.
void changeName(Table * grades, const string name, const int score ){
   if(!grades -> lookup(name)){
      cout << "Student does not exist." << endl;
   }else{
      if(*grades->lookup(name) != score){
         grades->insert(name,score);
         cout << "Student " << name << " score is updated to " <<score<< "." <<endl;
      }else{
         cout << "Student has already the same score." <<endl;
      }
   }       
}
// This fuction checks if the student is exist in the table.
// PRE: the name should not contain whitespace.
void lookUpName(Table * grades, const string name){
   int* lUp = grades->lookup(name);
   if (lUp != nullptr) {
      cout << "The score: " << *lUp << endl;
   } else {
      cout << "The student does not exist." << endl;
   }
}
// This fuction removes the student from the table if the student is exist.
// PRE: the name should not contain whitespace.
void removeName(Table * grades, const string name){
   if (!grades->remove(name)) {
      cout << "The student does not exist." << endl;
   } else {
      grades->remove(name);
      cout << "The student " << name << " has been removed." << endl;
   }
}
// This fuction prints messege to warn the given input is invalid And prints the valid inputs.
void invalid(){
   cout << "ERROR: invalid command." << endl;
   cout << "Please enter a command [insert, change, lookup, remove, print, size, stats, help, quit]" <<endl;
}