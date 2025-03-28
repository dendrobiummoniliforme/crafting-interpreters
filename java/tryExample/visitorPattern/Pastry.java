package tryExample.visitorPattern;

public abstract class Pastry {
    abstract void accept(PastryVisitor visitor);
    int price;
}

// Note for each of these classes, we have never implemented the
// price field! This is the power of the Visitor Pattern.
// Instead, we can define a price on the Pastry class, and define
// a Visitor to interact with the price field per sub-class, as-needed.
class Beignet extends Pastry {
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitBeignet(this);
    }
}

class Cruller extends Pastry {
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitCruller(this);
    }
}

class Eclair extends Pastry {
    @Override
    void accept(PastryVisitor visitor) {
        visitor.visitEclair(this);
    }
}

interface PastryVisitor {
    void visitBeignet(Beignet beignet); 
    void visitCruller(Cruller cruller);
    void visitEclair(Eclair eclair);
}