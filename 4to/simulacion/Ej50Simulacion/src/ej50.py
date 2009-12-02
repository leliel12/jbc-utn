###############################################################################
# IMPORTS
###############################################################################

import sys
import gears

from PyQt4 import QtGui
from PyQt4.QtCore import QAbstractTableModel, Qt, QVariant
from PyQt4.QtCore import Qt
from PyQt4.QtCore import QVariant

import qtbuilder

###############################################################################
# CONSTANTS
###############################################################################

builder = qtbuilder.QtBuilder()


# SETUP
builder.add_from_file("frame.ui")


###############################################################################
# CLASE PRINCIPAL
###############################################################################

class Ej50(QtGui.QMainWindow):
    def __init__(self, parent=None):
        super(Ej50, self).__init__(parent)
        self.ui = builder.get_object("Ui_MainWindow")
        self.g = gears.Gears()
        self.ui.setupUi(self)
        
        
    def on_pushButton_released(self):
        self.g.run(self.ui.spinBox.value())
        n_n = EventsTableModel(self.g.events)
        self.ui.tableView.setModel(n_n)
        self.ui.le_max_tierra.setText(str(self.g.max_tierra))
        self.ui.le_prom_tierra.setText(str(self.g.prom_tierra))
        self.ui.le_max_vuelo.setText(str(self.g.max_vuelo))
        self.ui.le_prom_vuelo.setText(str(self.g.prom_vuelo))
        self.ui.le_perc_sal.setText(str(self.g.per_sal))
        self.ui.le_perc_atr.setText(str(self.g.perc_atr))


###############################################################################
# MODELO DE LA TABLA
###############################################################################

class EventsTableModel(QAbstractTableModel): 
    def __init__(self, events): 
        super(EventsTableModel, self).__init__(None) 
        self.evts = events
 
    def rowCount(self, parent): 
        return len(self.evts) 
 
    def columnCount(self, parent): 
        return 7 
 
    def data(self, index, role): 
        if not index.isValid(): 
            return QVariant() 
        elif role != Qt.DisplayRole: 
            return QVariant()
        evt = self.evts[index.row()]
        data = None
        if index.column() == 0:
            data = evt.iteration
        elif index.column() == 1:
            data = "-".join(evt.names)
        elif index.column() == 2:
            data = evt.time
        elif index.column() == 3:
            data = evt.t_llegada
        elif index.column() == 4:
            data = evt.cant_vuelo
        elif index.column() == 5:
            data = evt.cant_pista
        elif index.column() == 6:
            data = evt.cant_estacion
        return QVariant(data) 

    def headerData(self, col, orientation, role):
        header = None
        if orientation == Qt.Horizontal and role == Qt.DisplayRole:
            if col == 0:
                header = "i"
            elif col == 1:
                header = "Eventos"
            elif col == 2:
                header = "Tiempo"
            elif col == 3:
                header = "Tiempo de Siguiente Llegada"
            elif col == 4:
                header = "Cantidad en Vuelo"
            elif col == 5:
                header = "Cantidad en Pista"
            elif col == 6:
                header = "Estacionados"
        if header:
            return QVariant(header)
        else:
            return QVariant()


###############################################################################
# MAIN
###############################################################################

if __name__ == "__main__":
    app = QtGui.QApplication(sys.argv)
    myapp = Ej50()
    myapp.show()
    sys.exit(app.exec_())
