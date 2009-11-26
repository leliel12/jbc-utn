# "qtbuilder.py" simple compiler for "QtDesigner 4.x" ".ui" files. 
# Copyright (C) 2009  - JBC <JBC dot Develop at gmail dot com >

# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Lesser General Public License as 
# published by the Free Software Foundation, either version 3 of the
# License, or (at your option) any later version.

# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU Lesser General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

################################################################################
# DOCS
################################################################################

"""simple compiler for 'QtDesigner 4.x' '.ui' files

usage in your code:
    
     import qtbuilder
     
     builder = qtbuilder = QtBuilder()
     builder.add_from_file('some_ui_file.ui')
     widget = builder.get_object('widget_name_in_your_ui_file')

"""


################################################################################
# META
################################################################################

__version__ = '0.1.1'
__license__ = 'LGPL3'
__author__ = 'JBC'
__since__ = '0.1'
__date__ = '2009-11-25'


################################################################################
# IMPORTS
################################################################################

from PyQt4 import uic


################################################################################
# CLASS
################################################################################

class QtBuilder(object):
    def __init__(self):
        self._widgets = {}
    
    def get_object(self, name):
        return self._widgets[name]()
    
    def add_from_file(self, filename):
        widget = uic.loadUiType(filename)[0]
        self._widgets[widget.__name__] = widget


################################################################################
# MAIN
################################################################################

if __name__ == "__main__":
    print __doc__
