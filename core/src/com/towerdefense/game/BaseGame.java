package com.towerdefense.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;

public abstract class BaseGame extends Game
{
    /**
     *  Stores reference to game; used when calling setActiveScreen method.
     */
    private static BaseGame game;

    /**
     *  Called when game is initialized; stores global reference to game object.
     */
    public BaseGame() 
    {        
        game = this;
    }

    /**
     *  Called when game is initialized, 
     *  after Gdx.input and other objects have been initialized.
     */
    public void create() 
    {        
        // prepare for multiple classes/stages/actors to receive discrete input
       
    }

    /**
     *  Used to switch screens while game is running.
     *  Method is static to simplify usage.
     */
    public static void setActiveScreen(ScreenAdapter s)
    {
        game.setScreen(s);
    }
}