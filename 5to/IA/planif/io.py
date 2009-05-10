#!/usr/bin/env python
#
#       io.py
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

'''
Read the plf source code and splits his 
lines ingnoring the comments an empty lines.
'''

import constants

def _get_lines(src):
    lines = []
    for line in src.splitlines():
        if line.strip():
            lines.append(line.rstrip())
    return lines

    
def _del_comments(lines):
    no_comments = []
    for line in lines:
        if constants.COMMENT in line:
            idx =  line.index(constants.COMMENT)
            no_comment = line[idx::]
            if no_comment:
                no_comments.append(no_comment)


def split_plf(plf_path):
    src = open(plf_path, 'r').read()
    lines = _get_lines(src)
    lines = _del_comments(lines)
    return lines
    
if __name__ == '__main__':
    print __doc__
