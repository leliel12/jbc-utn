class NotThisError(Exception):
    def __init__(selfm, msg="<no-msg>"):
        Exception.__init__(self)
        self.msg = msg

    def __str__(self):
        return self.msg

class Rule(object):
    def __init__(self, name, before, operation, post_condition, *pred):
        self.name = name
        self.params = params
        sel

class PlanifStructure(object):
    def __init__(self):
        self._symbols = []
        self._predicates = {}
        self._rules = {}
        self._init_state = None
        self._end_state = None

    def add_symbol(self, symbol):
        s = str(s)
        self._symbols.add(s)
