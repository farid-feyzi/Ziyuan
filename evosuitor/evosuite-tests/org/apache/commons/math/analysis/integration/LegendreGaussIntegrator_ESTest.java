/*
 * This file was automatically generated by EvoSuite
 * Tue Oct 17 07:07:12 GMT 2017
 */

package org.apache.commons.math.analysis.integration;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math.analysis.QuinticFunction;
import org.apache.commons.math.analysis.SinFunction;
import org.apache.commons.math.analysis.UnivariateRealFunction;
import org.apache.commons.math.analysis.integration.LegendreGaussIntegrator;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class LegendreGaussIntegrator_ESTest extends LegendreGaussIntegrator_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      LegendreGaussIntegrator legendreGaussIntegrator0 = new LegendreGaussIntegrator(5, 5);
      QuinticFunction quinticFunction0 = new QuinticFunction();
      double double0 = legendreGaussIntegrator0.integrate((UnivariateRealFunction) quinticFunction0, (-4801.7016937382305), (-2.6033824355191673E-8));
      assertEquals(2, legendreGaussIntegrator0.getIterationCount());
      assertEquals((-2.0427714058386583E21), double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      LegendreGaussIntegrator legendreGaussIntegrator0 = null;
      try {
        legendreGaussIntegrator0 = new LegendreGaussIntegrator(1475, 1475);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // 1,475 points Legendre-Gauss integrator not supported, number of points must be in the 2-5 range
         //
         verifyException("org.apache.commons.math.MathRuntimeException", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      LegendreGaussIntegrator legendreGaussIntegrator0 = new LegendreGaussIntegrator(4, 2553);
      assertEquals(1.0E-15, legendreGaussIntegrator0.getAbsoluteAccuracy(), 0.01);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      LegendreGaussIntegrator legendreGaussIntegrator0 = new LegendreGaussIntegrator(3, 6);
      assertEquals(3, legendreGaussIntegrator0.getMinimalIterationCount());
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      LegendreGaussIntegrator legendreGaussIntegrator0 = new LegendreGaussIntegrator(5, 5);
      SinFunction sinFunction0 = new SinFunction();
      try { 
        legendreGaussIntegrator0.integrate((UnivariateRealFunction) sinFunction0, (-4801.7016937382305), (-2.6033824355191673E-8));
        fail("Expecting exception: Exception");
      
      } catch(Exception e) {
         //
         // maximal number of iterations (5) exceeded
         //
         verifyException("org.apache.commons.math.analysis.integration.LegendreGaussIntegrator", e);
      }
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      LegendreGaussIntegrator legendreGaussIntegrator0 = new LegendreGaussIntegrator(2, 288);
      assertEquals(288, legendreGaussIntegrator0.getMaximalIterationCount());
  }
}