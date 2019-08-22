package model.heroes;

import lombok.Getter;
import lombok.Setter;
import model.ACharacter;
import model.items.weapons.AWeapon;

@Getter
@Setter
public abstract class AHero extends ACharacter {

    //constructor
    protected AHero(String name, int level) {
        //coordX, coordY at start are 0
        super(name, level, 0, 0);
        hp = getMaxHp();
    }

    //default constructor
    AHero() {
        this("Unknown", 1);
    }

    //hero stats getters
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int getBaseHp() {
        return 10;
    }

    @Override
    public int getBaseXp() {
        return 0;
    }

    @Override
    public int getBaseAttack() {
        return 10;
    }

    @Override
    public int getBaseDefence() {
        return 10;
    }

    @Override
    public int getBaseStrength() {
        return 10;
    }

    @Override
    public int getGrowthHp() {
        return 0;
    }

    @Override
    public int getGrowthXp() {
        return 0;
    }

    @Override
    public int getGrowthAttack() {
        return 0;
    }

    @Override
    public int getGrowthDefence() {
        return 0;
    }

    @Override
    public int getGrowthStrength() {
        return 0;
    }

    public String getStatString() {
        String str;
        str = "MaxHP :" + getGrowthHp();
        str = str + "Attack :" + getGrowthAttack();
        str = str + "Defence :" + getGrowthDefence();
        str = str + "Strength :" + getGrowthStrength();
        return str;
    }

    //fetching from the individual hero classes
    public abstract String getClassDescription();
    public abstract HeroAbility getAbility();

    public enum HeroAbility {
        None,
        SecondWind
    }

    //xp variables
    //the base xp is kept in ACharacter
    @Getter
    protected int xpTolevel;
    protected int xpCurrent;
    protected int xpTemp;

    public boolean getXp(int xpNew) {
        xp = xp + xpNew;

        if (xp >= xpTolevel) {
            xpTemp = xp - xpTolevel;
            this.levelUp();
            return true;
        }
        return false;
    }

    public int getXpTolevel(){
        return xpTolevel;
    }

    public int getXpCurrent() {
        return xpCurrent;
    }

    @Override
    protected void levelUp(int newLevel) {
        super.levelUp(newLevel);

        xpTolevel = level * 1000;
        xp = xpTemp;

        //TODO item stats & xp balancing
    }


    //abilities common to all heroes
    public boolean rest(int healing) {
        if (hp == 0 || hp == maxHp)
            return false;

        hp = hp + healing;
        if (hp > maxHp) {
            hp = maxHp;
        }
        return true;
    }

    //TODO roll to attack

    //Items
    //TODO helms defence
    protected AWeapon weapon = null;

    public AWeapon getWeapon() {
        return weapon;
    }

    public void equipWeapon(AWeapon tempWeapon) {
        if (weapon != null){
            //replacing attack with the new weapon's attack then apply afterwards
            attack = attack - weapon.getAttack();
        }

        weapon = tempWeapon;
        attack = attack + weapon.getAttack();
    }

}
