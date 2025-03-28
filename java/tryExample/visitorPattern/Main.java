package tryExample.visitorPattern;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create some similar pastries
        // note, most importantly, this is a Pastry! Not an Eclair or Cruller 
        // or Beignet Type for each constructor.
        List<Pastry> pastries = Arrays.asList(
             new Beignet(),
             new Cruller(),
             new Eclair()
        );

        // Create a visitor with some "action" for each pastry type
        PastryVisitor pastryVisitor = new PastryVisitor() {
            @Override
            public void visitBeignet(Beignet beignet) {
                System.out.println("PastryVisitor: This is a yummy Beignet!");
            }

            @Override
            public void visitCruller(Cruller cruller) {
                cruller.price = 5;
                System.out.println("PastryVisitor: Here is a delicious Cruller! its price: " + cruller.price);
            }

            @Override
            public void visitEclair(Eclair eclair) {
                // then, each time we need to add functionality to a sub-pastry
                // we only need to edit the sub-pastry that requires that functionality.
                // Other sub-pastries do not need to define the same behaviour.
                eclair.price = 5;
                System.out.println("PastryVisitor: Here is a delicious Eclair it price: " + eclair.price);
            }
        };
        PriceVisitor priceVisitor = new PriceVisitor(-1);

        // This is important when traversing an AST
        // as we will have many sub-expressions (Expr) that will define their
        // own related functionality, but are required to be Expr Nodes within the tree.
        for (Pastry pastry: pastries) {
            // If you want order dependence then you can
            // simply traverse each pastry 
            // visitor cominbation in their own for-loop, keeping the procedure
            // in order of visitor and pastry.
            pastry.accept(pastryVisitor);
            pastry.accept(priceVisitor);
        }
    }
}

