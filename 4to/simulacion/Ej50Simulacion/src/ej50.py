import sys
import gears
from PyQt4 import QtCore, QtGui, uic
from frame import Ui_MainWindow


class Ej50(QtGui.QMainWindow):
    def __init__(self, parent=None):
        super(Ej50, self).__init__(parent)
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)
        
    def on_pushButton_clicked(self):
        g = gears.Gears()
        g.run(100)
        for e in g.iter_events():
            print e
        

if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    myapp = Ej50()
    myapp.show()
    sys.exit(app.exec_())
