/*  
 * Copyright 2012 xavi.ferro
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */   
package net.xiron.pattern.statemachine.annotated;

import junit.framework.Assert;
import net.xiron.pattern.statemachine.annotated.util.DumbRedirectController;
import net.xiron.pattern.statemachine.annotations.AnnotatedControllerFactory;
import net.xiron.pattern.statemachine.annotations.AnnotatedControllerProcessor;

import org.testng.annotations.Test;

public class RedirectOnEnterTransitionTest {
    @Test
	public void testRedirect() throws Exception {
        AnnotatedControllerFactory f = new AnnotatedControllerFactory();
        AnnotatedControllerProcessor processor = f.createNonReentrantProcessor(new DumbRedirectController());
        processor.processEvent(DumbRedirectController.EVENT_AA, null);
        net.xiron.pattern.statemachine.StateMachine sm = processor.getStateMachine();
        String endState = sm.getCurrentState();
        Assert.assertEquals(endState, DumbRedirectController.STATE_B);
    }
}
