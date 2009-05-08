#!/usr/bin/env python

import random
import sys

def _get_questions(file_path):
	f = open(file_path)
	questions = f.read().replace("\n","").split('@@')
	f.close()
	random.shuffle(questions)
	questions = [q for q in questions if q]
	return questions
	
def _get_some_questions(questions, number):
	q_number = len(questions)-1
	selected = []
	for i in range(number):
		idx = random.randint(0, q_number)
		q = questions[idx]
		selected.append(q)
	return selected

if __name__ == '__main__':
	file_path = sys.argv[1]	
	questions = _get_questions(file_path)
	option = ""
	while option.lower() != 'q':
		selected = _get_some_questions(questions, 5)
		for s in selected:
			print s
		print "\nPresione 'q' para salir..."
		option = raw_input()
