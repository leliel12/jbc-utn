#!/usr/bin/env python
#
#       strip_tokenizer.py
#       
#       Copyright 2009 Juan B Cabral <juan@Brother-Eye>
#       
#       This program is free software; you can redistribute it and/or modify
#       it under the terms of the GNU General Public License as published by
#       the Free Software Foundation; either version 3 of the License, or
#       (at your option) any later version.
#       
#       This program is distributed in the hope that it will be useful,
#       but WITHOUT ANY WARRANTY; without even the implied warranty of
#       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#       GNU General Public License for more details.
#       
#       You should have received a copy of the GNU General Public License
#       along with this program; if not, write to the Free Software
#       Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
#       MA 02110-1301, USA.

'''Splits strip source doce in lines ingnoring the comments an empty lines.'''

import constants

################################################################################
# Line Class
################################################################################

class Line(object):
    def __init__(self, line_number, statement):
        self.statement = str(statement)
        self.line_number = int(line_number)
        
    def __str__(self):
        return "%i> %s" % (self.line_number, self.statement)


################################################################################
# Globals
################################################################################

def _is_empty(statement):
    return not statement.strip()


def _adjust(statement):
    return statement.rstrip()

    
def _del_comments(statement):
    if constants.RW_COMMENT in statement:
        idx =  statement.index(constants.RW_COMMENT)
        statement = statement[0:idx]
    return statement


def _append(line_number, statement, line_list):
    if not _is_empty(statement):
        line = Line(line_number, statement)
        line_list.append(line)
    return line_list

def _merge_multi_line(statement_list):
    print "DO merge_multi_line"
    return statement_list
        

def strip_tokenizer(strip_src):
    line_list = []
    line_number = 0
    for statement in strip_src.splitlines():
        if not _is_empty(statement):
            line_number += 1
            statement = _del_comments(statement)
            statement = _adjust(statement)
            line_list = _append(line_number,statement, line_list)
    line_list = _merge_multi_line(line_list)
    return tuple(line_list)
 

################################################################################
# Main
################################################################################
    
if __name__ == '__main__':
    print __doc__
    plf = open("/home/juan/UTN/5to/IA/strips_interpreter/strip_self_tutorial.strip").read()
    for line in strip_tokenizer(plf):
        print line
