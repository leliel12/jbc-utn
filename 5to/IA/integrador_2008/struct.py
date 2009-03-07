#!/usr/bin/env python

class ILine(object):


    def __init__(self, text_line):
        self.nums = []
        for n in text_line.split():
            if len(n.strip()) > 0:
                self.nums.append(n)


    def __str__(self):
        msg = "Line: " + str(self.nums)
        return msg 


class IFile(object):


    def __init__(self, path):
        self.path = path
        self.lines = []
        self._read()


    def _read(self):
        f = open(self.path)
        f_content = f.read()
        f.close()
        self._split_lines(f_content)


    def _split_lines(self, text):
        lines = text.split("\n")
        for l in lines:
            if len(l.strip()) > 0:
                line = ILine(l)
                self.lines.append(line)


    def __str__(self):
        msg  = "File: %s" % str(self.path)
        for l in self.lines:
            msg += "\n\t%s" % str(l)
        return msg
