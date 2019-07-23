package model.heroes;

public class Fighter extends AHero {

    //constructors
    public Fighter (String name, int level){
        super(name, level);
    }

    Fighter(){
        this("Fighter", 1);
    }

    //Stat changes for the class
    @Override
    public int getGrowthAttack() {
        return super.getGrowthAttack() + 2;
    }

    @Override
    public int getGrowthDefence() {
        return super.getGrowthDefence() + 1;
    }

    @Override
    public int getGrowthStrength() {
        return super.getGrowthStrength() + 1;
    }

    //class and ability descriptions
    @Override
    public String getClassDescription() {
        String str = "A fighter is a versatile, weapons-oriented warrior who fights using skill, strategy and tactics.";
                str = str + " Ability: You are able to use Second Wind in combat, recovering some damage.";
                return str;
    }

    @Override
    public HeroAbility getAbility() {
        return HeroAbility.SecondWind;
    }

    //TODO Hero Abilities
}
