package net.xiron.pattern.statemachine;

/**
 * Each transition is splitted into three phases. The phase most used should be the
 * @link{#phaseTransition} one.
 * 
 * They are executed sequentially:
 * - First, the method @link{#phaseExitState} is executed. Allows cancelling the transition.
 * - Second, the method @link{#phaseTransition} is executed. 
 * - And third, the method @link{#phaseEnterState} is executed. It allows forcing another
 *   event to be processed without releasing the lock. This might be useful for some
 *   circumstances (specially ghost-like condition states that we need to check a lot of
 *   conditions for performing other actions)
 *                      
 * @author xavi.ferro
 */

public interface StateMachineController {
    /**
     * First phase of the transition.The one and only phase that allows stopping the transition. 
     * Returning false will stop the event propagation.
     * 
     * @return true in case we want to continue with the transition. 
     *         false will cancel the transition and will keep the current state as
     *               it was before the transition.
     */
    boolean phaseExitState(TransitionEvent event);
    
    /**
     * The transition phase itself.
     */
    void phaseTransition(TransitionEvent event);
    
    /**
     * Third step, executed when entering a state.
     * 
     * @return the event we want to execute next right after this transition -as there
     *         might be other threads trying to perform other transitions-. 
     *         @Null means we don't want to do anything special.
     */
    PhaseEnterResult phaseEnterState(TransitionEvent event);
}
