# encode: utf-8

import sys, random, os, yaml

questions_file = open(sys.path[0] + os.path.sep + "questions.yaml")

questions = yaml.load(questions_file.read())

questions_file.close()

_option = ""
idx = 1

print "PULSE <Q> PARA SALIR..."
print "-" * 80, "\n"

while _option.lower() != "q":
    q = random.choice(questions)
    print idx, ". ", q
    idx += 1
    _option = raw_input()
    
