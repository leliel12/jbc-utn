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
from PyQt4.uic.Compiler import compiler


################################################################################
# CLASS
################################################################################

class QtBuilder(object):
    def __init__(self):
        self._widgets = {}
    
    def _register(self, widget):
        self._widgets[widget.__name__] = widget
    
    def get_object(self, name):
        return self._widgets[name]()
        
    def get_objects(self, names):
        return [self.get_object(name) for name in names]
    
    def add_from_file(self, filename):
        widget = uic.loadUiType(filename)[0]
        self._register(widget)
        
    def add_from_string(self, string):
        """Based on pyqt loadUiType"""
        import sys
        from PyQt4 import QtGui
        if sys.hexversion >= 0x03000000:
            from PyQt4.uic.port_v3.string_io import StringIO
        else:
            from PyQt4.uic.port_v2.string_io import StringIO
        code_string = StringIO()
        ui_string = StringIO()
        ui_string.write(string)
        ui_string.seek(0)
        winfo = compiler.UICompiler().compileUi(ui_string, code_string)
        ui_globals = {}
        exec(code_string.getvalue(), ui_globals)
        self._register(ui_globals[winfo["uiclass"]])


################################################################################
# MAIN
################################################################################

if __name__ == "__main__":
    print __doc__
