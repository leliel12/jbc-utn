#!/usr/bin/env python
#
#       constants.py
#       
#       Copyright 2009 Juan B Cabral <jbcabral_89@yahoo.com.ar>
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

'''Constants for planification project.'''


################################################################################
# Struct Types
################################################################################

ST_PREDICATE = 'predicate'
ST_RULE = 'rule'
ST_PRECONDITION_LIST = 'precondition_list'
ST_ERASE_LIST = 'erase_list'
ST_ADD_LIST = 'add_list'
ST_INIT_STATE = 'init_state'
ST_END_STATE = 'end_state'


################################################################################
# Reserved Words
################################################################################

RW_COMMENT = '#'
RW_INIT_STATE = 'init'
RW_END_STATE = 'end'
RW_PRECONDITION_LIST = 'P='
RW_ERASE_LIST = 'E='
RW_ADD_LIST = 'A='
RW_AND_CHAR = '^'


################################################################################
# Main
################################################################################

if __name__ == '__main__':
    print __doc__
