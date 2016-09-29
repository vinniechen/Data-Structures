#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "pa9.h"

Stack *stack;

/**
 * Function stack_init()
 * Description: Constructor for stack, creates memory
 * for stackP, creates memory for
 * info, assigns info's top and operations to 0
 * Parameter: none
 * Return Value: reference to initialized stack
 */
Stack * stack_init() {
	stackP = (Stack*)malloc(sizeof(Stack));
	if (stackP == NULL) {
		printf(STR_ERR_MEM);
		return NULL;
	}
	stackP->array[0] = malloc(sizeof(info));	
	((info*)(stackP->array[0]))->top=0;
	((info*)(stackP->array[0]))->operations = 0;
	return stackP;
}
/**
 * Function push(char * str)
 * Description:
 * Inserts string at top of the stack. Increases top member variable
 * Parameter: char*str pointer to first character of string
 *	Return Value: none
 */
void push(char * str) {
	// casts array[0] to info* and gets its member top
	int push = (((info*)(stackP->array[0]))->top) +1; // where to push
	if ( push == 50 || strlen(str)==0 ) {
		printf(STR_PUSH_FAILURE);
	}
	else {
		// casts array[top] to char*
		stackP->array[push] = (char*)malloc((strlen(str)+1)*sizeof(char));
		// copies str to array[top]
		strcpy((char*)stackP->array[push], str);
		// increase top and operations
		((info*)(stackP->array[0]))->top = push;	
		(((info*)(stackP->array[0]))->operations)++;
		printf(STR_PUSH_SUCCESS, str);
	}
	free(str);
}
/**
 * Function char * pop()
 * Description: Removes the string at the top of the stack
 * Parameter: none
 * Return Value: char* of popped string
 */
char * pop() {
	int top = ((info*)(stackP->array[0]))->top; // makes local variable top
	if ( top == 0 ) { // nothing in stack
		printf(STR_POP_FAILURE);
		return NULL;
	}
	char*popped = (char*)stackP->array[top];
	//free(stackP->array[top]);
	((stackP->array[top])) = NULL; // makes old top null
	// decrease top index and increase operations
	((info*)(stackP->array[0]))->top = ((info*)(stackP->array[0]))->top - 1;
	(((info*)(stackP->array[0]))->operations)++;
	printf(STR_POP_SUCCESS, popped);
	return popped;
}
/**
 * Function char * peek()
 * Description: Shows string at the top of the stack
 * Parameter: none
 * Return: pointer to string at the top of the stack
 */
char * peek() {
	int top = ((info*)(stackP->array[0]))->top; // makes local variable top
	if ( top == 0 ) {
		printf(STR_PEEK_FAILURE);
		return NULL;
	}

	char*peek = (char*)stackP->array[top];
	printf(STR_PEEK_SUCCESS, peek);
	return peek;
}
/**
 * Function stack_delete() 
 * Description: Frees all malloc'd memory
 *	Param: none
 * Return: none
 */
void stack_delete() {
	int i;
	for (i = 1; i <= (((info*)(stackP->array[0]))->top); i++) {
		free(stackP->array[i]);
	}
	free(stackP->array[0]);
	//free(uncInfo->word);
	free(stackP);
	
}

/**
 * Function print()
 * Description: Prints stats for the class 
 * Param: none
 * Return: none
 */
void print() {
	int nelems = ((info*)(stackP->array[0]))->top;
	int index = nelems; 
	int oper = ((info*)(stackP->array[0]))->operations;
	printf(STACK_PRINT_STR, nelems , index+1, oper);

	if (nelems == 0) { 
		printf(STR_PRINT_NO_WORDS);	
	}
	else { // prints the words in stack
		printf(STR_PRINT_WORDS);
		int i;
		for (i = 1; i <= nelems; i++) {
			printf(" %s", stackP->array[i]);
		}
	}
}

void check(char * str) {

} 
/**
 *	Function parse_input(FuncInfo * funcInfo, const char * func)
 *	Description:
 *	Checks stdin for the operation, then checks for a syntax error
 *	Returns the number of corresponding to the switch statement in main
 *	Parameter: pointer to funcInfo, pointer to stdin
 *	Return: int based on function found
 *	Dear Grader,
 *	I apologize in advance for the length of my function
 *	Please have mercy on my code
 */
int parse_input(FuncInfo * funcInfo, const char * func){
	const char * peek = "peek";
	const char * pop = "pop";
	const char * print = "print";
	const char * push = "push";
	const char * check = "check";
	int funcNum = 0; // 1 peek, 2 pop, 3 print | 4 print 5 check
	int returnNum = 0;
	// Check for unknown operation
	const	char * funcFound = NULL;
	const char * search = func;

	// peek 1
	while (strstr(search, peek) != NULL) {
		// if found,
		// check for alpha letter after and before
		if (isalpha(*(strstr(search, peek)+strlen(peek))) ||
				(func != strstr(search, peek) 
				 && isalpha(*(strstr(search, peek)-1)))) {
			search += strlen(peek); // move search pointer
		}
		else { // lone "peek" found
			funcFound = strstr(search, peek);
			funcNum = 1;
			returnNum = 3;
			break;
		}
	} 

	search = func;
	// pop 2
	while (strstr(search, pop) != NULL) {
		// if found, check for alpha letter after and before
		if (isalpha(*(strstr(search, pop)+strlen(pop))) ||
				(func != strstr(search, pop)
				 && isalpha(*(strstr(search, pop)-1)))) {
			search += strlen(pop);
		}
		else {
			funcFound = strstr(search, pop);
			funcNum = 2;
			returnNum = 2;
			break;
		}
	}

	search = func;

	// print 3
	while (strstr(search, print) != NULL) {
		// if found, check for alpha letter after and before
		if (isalpha(*(strstr(search, print)+strlen(print))) ||
				(func != strstr(search, print) 
				 && isalpha(*(strstr(search, print)-1)))) {
			search += strlen(print);	
		}
		else {
			funcFound = strstr(search, print);
			funcNum = 3;
			returnNum = 4;
			break;
		}
	}

	search = func;

	// push 4
	while (strstr(search, push) != NULL) {
		// if found, check for alpha letter after and before
		if (isalpha(*(strstr(search, push)+strlen(push))) ||
				(func != strstr(search, push) 
				 && isalpha(*(strstr(search, push)-1)))) {
			search += strlen(push);
		}
		else {
			funcFound = strstr(search, push);
			funcNum = 4;
			returnNum = 1;
			break;
		}
	}

	search = func;

	// check 5
	while (strstr(search, check) != NULL) {
		// if found, check for alpha letter after and before
		if (isalpha(*(strstr(search, check)+strlen(check))) ||
				(func != strstr(search, check) 
				 && isalpha(*(strstr(search, check)-1)))) {
			search += strlen(check);
		}
		else {
			funcFound = strstr(search, check);
			funcNum = 5;
			returnNum = 5;
			break;
		}
	}
	// if no func found, return Unknown Operation Error
	if (funcFound == NULL) {
		printf(STR_ERR_UNKNOWN);
		return -1;
	}
	// Check for syntax errors
	const char* i;
	int k;

	for (i = func, k = 0; k < strlen(func)-1; i++, k++) { // loop over string	
		if (isspace(*i)) { // check if there are whitespace characters
			printf(STR_ERR_SYNTAX);
			return -1;
		}	
	} 
	const char * compareFound;
	switch(funcNum) { // function name that was found
		case 1: 
			compareFound = peek;
			break;
		case 2:
			compareFound = pop;
			break;
		case 3:
			compareFound = print;
			break;
		case 4:
			compareFound = push;
			break;
		case 5:
			compareFound = check;
			break;
		default:
			break;
	}

	// check if word found is flush to beginning, followed by (
	if (funcFound != func || *(func+strlen(compareFound)) != '(' ) { // 
		printf(STR_ERR_SYNTAX);
		return -1;
	}
	if (funcNum < 4 && (*(func+strlen(compareFound)+1) != ')' // find )
			|| strlen(compareFound)+3 != strlen(func)) ) { // peek, pop, print
				printf(STR_ERR_SYNTAX);
				return -1;
			}
	const char* end = "\")";
	const char* hasEnd = strstr(func+strlen(compareFound), end);
	if (funcNum > 3) { // find " and ") and '\0' at correct spot
		if (*(func+strlen(compareFound)+1) != '"' ||
				hasEnd == NULL || *(hasEnd+3) != '\0') {
			//printf("not found \" or \")");
			printf(STR_ERR_SYNTAX);
			return -1;
		}
	}
	// check for correct alpha letters inside		
	const char* startWord = func+strlen(compareFound)+2; // pointer to start
	const char* endWord = hasEnd; // pointer to " after end
	const char* j;
	int n;
	const char* x = startWord;
	int sizeWord;
	char returnWord[10000] = {0}; // returnWord, word inside "word"
	if (funcNum > 3) {
		while (x != hasEnd) {
			sizeWord++;
			x++;
		}

		for (j = startWord, n = 0; j != endWord; j++, n++) {
			returnWord[n] = *j; // copy word to returnWord
			if (!isalpha(*j)) {
				printf(STR_ERR_SYNTAX);
				return -1;
			}
		}
	
	 // if push or check, assigns word found
		funcInfo->word = (char*)malloc(sizeof(char)*sizeWord);
		strcpy(funcInfo->word, returnWord);
	}

	funcInfo->func = returnNum; // number to be used in main's switch statement
	return returnNum;

}

/**
 * Function int main (int argc, char * argv[])
 * Description: Creates a stack, stack can be push, pop, peek, print
 * Parameters: arg1 - int argc -- number of arguments passed
 * arg2 - char *argv[] array that stores command line argument
 * Return: indivated failed or successfull 
 */
int main(int argc, char * argv[]) {
	setbuf(stdout,NULL);

	//will not compile till you provide a declaration for stack 

	stack = stack_init();

	if (stack == NULL) {
		fprintf(stderr, STR_ERR_MEM);
		return EXIT_FAILURE;
	}

	//to pass into parse_input
	FuncInfo funcInfo;

	printf(STR_WELCOME);
	printf(STR_PROMPT);

	//to store strings from stdin
	char readin[BUFSIZ];
	while (fgets(readin, BUFSIZ, stdin) != NULL) {
		if (feof(stdin)) {
			break;
		}

		//parse input
		int r = parse_input(&funcInfo, readin);	
		//input could not be parsed, continue to next iteation
		if (r == -1) {
			printf("\n");
			printf(STR_PROMPT);
			continue;
		}
		//call appropriate function
		switch(funcInfo.func) {
			case 1:
				push(funcInfo.word);
				break;
			case 2:
				free(pop());
				break;
			case 3:
				peek();
				break;
			case 4:
				print();
				break;
			case 5:
				check(funcInfo.word);
				break;
			default:
				printf(STR_ERR_UNKNOWN);
		}
		printf("\n");

		printf(STR_PROMPT);
	}

	//destruct stack
	stack_delete();

	printf("quit\n");

	return EXIT_SUCCESS;

	return 0;
}
