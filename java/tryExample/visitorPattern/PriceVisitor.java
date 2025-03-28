package tryExample.visitorPattern;

public class PriceVisitor implements PastryVisitor {
        private final int amount;

        PriceVisitor(int amount) {
            this.amount = amount;
        }
    
        @Override
        public void visitBeignet(Beignet beignet) {
            // Note we don't need to do anything for this class
            // other than implement this override in the PriceVisitor
            // but not the Beignet class!
            System.out.println("PriceVisitor: Beignet's are always free :)");
        }
    
        @Override
        public void visitCruller(Cruller cruller) {
            cruller.price += amount;
            System.out.println("PriceVisitor: Reduced Cruller price to: " + cruller.price);
        }
    
        @Override
        public void visitEclair(Eclair eclair) {
            eclair.price += amount;
            System.out.println("PriceVisitor: Reduced Eclair price to: " + eclair.price);
        }
    }
