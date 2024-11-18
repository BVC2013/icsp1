import java.util.*;

class GuitarNode {
    //declare stuffs
    //data type number one
    String question;
    //data type number 2
    Map<Integer, GuitarNode> children;
    String recommendation;
    //data type number 3
    int score;

    //constructor init
    public GuitarNode(String question) {
        this.question = question;
        this.children = new HashMap<>();
        this.recommendation = "";
        this.score = 0;
    }
    //self descriptive but adds child node to parent node
    public void addChild(int choice, GuitarNode child) {
        this.children.put(choice, child);
    }
    //self descriptive name
    public boolean isLeaf() {
        return children.isEmpty();
    }
}

public class GuitarPathTree {
    //root node var
    private final GuitarNode root;
    //constructor for aforementioned rooty
    public GuitarPathTree() {
        root = buildTree();
    }
    //tree, built question by question
    private GuitarNode buildTree() {
        GuitarNode root = new GuitarNode("What type of guitar are you looking for?\n1. Acoustic\n2. Electric\n3. Bass");

        // Acoustic branch
        GuitarNode acoustic = new GuitarNode("What kind of music do you want to play?\n1. Fingerstyle\n2. Strumming");
        GuitarNode brightTone = new GuitarNode("Do you prefer a bright tone?\n1. Yes\n2. No (Warm tone)");
        GuitarNode cutaway = new GuitarNode("Do you need a cutaway design for easier access to high frets?\n1. Yes\n2. No");
        //cool guitar names
        brightTone.addChild(1, createLeaf("Fender FA-125 Dreadnought", "Gibson J-45 Standard", "Jackson Dinky JS22", 80, 85, 75));
        brightTone.addChild(2, createLeaf("Fender CD-60S", "Gibson Songwriter", "Jackson JS32 Dinky", 85, 90, 78));
        cutaway.addChild(1, createLeaf("Fender Malibu Player", "Gibson L-00 Standard", "Jackson JS22 DKA", 88, 86, 80));
        cutaway.addChild(2, createLeaf("Fender Redondo", "Gibson Hummingbird", "Jackson Soloist SL3", 87, 89, 82));

        acoustic.addChild(1, brightTone);
        acoustic.addChild(2, cutaway);

        // Electric branch
        GuitarNode electric = new GuitarNode("What style of music do you want to play?\n1. Rock\n2. Jazz\n3. Metal");
        GuitarNode rock = new GuitarNode("Do you prefer single-coil pickups?\n1. Yes\n2. No");
        GuitarNode jazz = new GuitarNode("Do you prefer a hollow-body guitar?\n1. Yes\n2. No");
        GuitarNode metal = new GuitarNode("Do you want active pickups?\n1. Yes\n2. No");
        //cool guitar names
        rock.addChild(1, createLeaf("Fender Stratocaster", "Gibson Les Paul Studio", "Jackson SL3 Soloist", 90, 85, 88));
        rock.addChild(2, createLeaf("Fender Telecaster", "Gibson SG Standard", "Jackson Rhoads RR3", 88, 86, 84));
        jazz.addChild(1, createLeaf("Fender Telecaster Thinline", "Gibson ES-335", "Jackson Monarkh SC", 87, 92, 84));
        jazz.addChild(2, createLeaf("Fender Jazzmaster", "Gibson L-5 CES", "Jackson Pro Dinky", 86, 90, 83));
        metal.addChild(1, createLeaf("Fender Jim Root Telecaster", "Gibson Flying V", "Jackson Warrior WRX24", 88, 94, 91));
        metal.addChild(2, createLeaf("Fender HH Player", "Gibson Explorer", "Jackson Soloist SLX DX", 86, 89, 90));

        electric.addChild(1, rock);
        electric.addChild(2, jazz);
        electric.addChild(3, metal);

        // Bass branch
        GuitarNode bass = new GuitarNode("How many strings do you prefer?\n1. Four\n2. Five\n3. Six");
        GuitarNode fourStrings = new GuitarNode("Do you play mostly slap bass?\n1. Yes\n2. No");
        GuitarNode fiveStrings = new GuitarNode("Do you want a fretless bass?\n1. Yes\n2. No");
        GuitarNode sixStrings = new GuitarNode("Do you prefer a wide neck?\n1. Yes\n2. No");
        //cool guitar names
        fourStrings.addChild(1, createLeaf("Fender Jazz Bass", "Gibson EB Bass", "Jackson JS3 Concert Bass", 89, 85, 86));
        fourStrings.addChild(2, createLeaf("Fender Precision Bass", "Gibson Thunderbird", "Jackson CBXNT IV", 87, 83, 82));
        fiveStrings.addChild(1, createLeaf("Fender Fretless Jazz Bass", "Gibson Ripper Bass", "Jackson CBX V", 90, 88, 89));
        fiveStrings.addChild(2, createLeaf("Fender American Deluxe", "Gibson RD Artist", "Jackson Concert Bass CBX", 88, 87, 86));
        sixStrings.addChild(1, createLeaf("Fender VI Bass", "Gibson Victory Bass", "Jackson Spectra JS3V", 85, 84, 83));
        sixStrings.addChild(2, createLeaf("Fender Dimension Bass", "Gibson Grabber Bass", "Jackson Spectra JS2", 84, 83, 82));

        bass.addChild(1, fourStrings);
        bass.addChild(2, fiveStrings);
        bass.addChild(3, sixStrings);

        root.addChild(1, acoustic);
        root.addChild(2, electric);
        root.addChild(3, bass);

        return root;
    }

    private GuitarNode createLeaf(String fender, String gibson, String jackson, int fenderScore, int gibsonScore, int jacksonScore) {
        GuitarNode leaf = new GuitarNode("");
        leaf.recommendation = String.format("Fender: %s (%d) | Gibson: %s (%d) | Jackson: %s (%d)", 
                                            fender, fenderScore, gibson, gibsonScore, jackson, jacksonScore);
        leaf.score = Math.max(fenderScore, Math.max(gibsonScore, jacksonScore));
        return leaf;
    }
    //actual user stuffs 
    public void guideUser() {
        //user input
        Scanner scanner = new Scanner(System.in);
        GuitarNode current = root;

        //outputting leaf level data
        while (!current.isLeaf()) {
            System.out.println(current.question);
            int choice = scanner.nextInt();
            if (current.children.containsKey(choice)) {
                current = current.children.get(choice);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Your guitar compatibility score is: " + current.score + "/100");
        System.out.println("Recommended Guitars: " + current.recommendation);
    }

    public static void main(String[] args) {
        GuitarPathTree guitarTree = new GuitarPathTree();
        guitarTree.guideUser();
    }
}
