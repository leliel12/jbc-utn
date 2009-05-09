#!/usr/bin/env python

import random
import sys


class Questions(object):
    def __init__(self, file_path):
        self.questions = self._generate_questions(file_path)

    def _generate_questions(self, file_path):
        f = open(file_path)
        read = f.read()
        f.close()
        read = read.replace("\n","")
        questions = [q.strip() for q in read.split("@@")]
        questions = [q for q in questions if q.strip()]
        questions = [q for q in questions if not q.startswith('#')]
        random.shuffle(questions)
        return questions
        

    def random_questions(self, number):
        n = len(self.questions)
        if number > n:
            raise ValueError("number must be <= %i: %i" % (n,number))        
        selected = set()
        while len(selected) < number:
            idx = random.randint(0, n - 1)
            q = self.questions[idx]
            selected.add(q)
        return selected

if __name__ == '__main__':
    file_path = sys.argv[1]
    noq = int(sys.argv[2])
    q = Questions(file_path)
    option = ""
    while option.lower() != 'q':
        selected = q.random_questions(noq)
        for s in selected:
            print s
        print "\nPresione 'q' para salir..."
        option = raw_input()
