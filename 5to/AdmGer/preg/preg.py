#!/usr/bin/env python

import random
import sys


class Questions(object):
    def __init__(self, file_path):
        self.questions = self._generate_questions(file_path)
        self.question_len = len(self.questions)

    def _generate_questions(self, file_path):
        f = open(file_path)
        read = f.read()
        f.close()
        read = read.replace("\n","")
        questions = [q for q in read.split("@@") if q]
        random.shuffle(questions)
        return questions
        
    def random_questions(self, number):
        n = self.question_len -1
        selected = []
        for i in range(number):
            idx = random.randint(0, n)
            q = self.questions[idx]
            selected.append(q)
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
