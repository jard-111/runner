public class Camera {
    private double x;//position en x de la camera (on a choisit un double pour avoir un deplacement moin sacade)
    private int y;//position en Y de la camera (je ne m'en suis pas servi mais il aurait ete possible de faire un ressort en Y aussi)
    private double ax;//acceleration de la camera en x
    private double vx;//vitesse de la camera en y

    //constantes du ressort
    private float km= (float)3;
    private float fm= (float) 1.2;

    //constructeur
    public Camera(int pX, int pY){
        this.x=pX;
        this.y=pY;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    // physique du ressort appliquee en X a la camera par rapport a la position du hero
    public void physique(Heros hero){
        this.ax=  (km*(hero.getX()-this.x)-fm*vx);
        this.vx+=  (ax*0.016);
        this.x+= (vx*0.016);
    }

    //permet de remettre la position de la camera a son origine quand on relance le jeu
    //on aurait pu faire une fonction reset qui empecherais de donner a la camera n'importe quelle position mais un setX permet de faire des essais
    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

}
