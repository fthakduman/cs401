package com.composite.modelbasedtesting3;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

public class Model3 implements FsmModel {

	public enum Page {
		INITIAL, YKB, BANK1, BANK2, BANK3, BANK4
	}

	private Page mPage = Page.INITIAL;

	private Adapter3 mAdapter = new Adapter3();

	@Action
	public void ykb() {
		try {
			mAdapter.ykb();
			mPage = Page.YKB;
		} catch (Exception e) {
			mPage = Page.INITIAL;
		}
	}

	public boolean ykbGuard() {
		return mPage == Page.INITIAL;
	}

	@Action
	public void bank1() {
		try {
			mAdapter.bank1();
			mPage = Page.BANK1;
		} catch (Exception e) {
			mPage = Page.INITIAL;
		}
	}

	public boolean bank1Guard() {
		return mPage == Page.YKB;
	}

	@Action
	public void bank2() {
		try {
			mAdapter.bank2();
			mPage = Page.BANK2;
		} catch (Exception e) {
			mPage = Page.INITIAL;
		}
	}

	public boolean bank2Guard() {
		return mPage == Page.BANK1;
	}

	@Action
	public void bank3() {
		try {
			mAdapter.bank3();
			mPage = Page.BANK3;
		} catch (Exception e) {
			mPage = Page.INITIAL;
		}
	}

	public boolean bank3Guard() {
		return mPage == Page.BANK2;
	}

	@Action
	public void bank4() {
		try {
			mAdapter.bank4();
			mPage = Page.BANK4;
		} catch (Exception e) {
			mPage = Page.INITIAL;
		}
	}

	public boolean bank4Guard() {
		return mPage == Page.BANK3;
	}

	@Override
	public Object getState() {
		return mPage;
	}

	@Override
	public void reset(boolean arg0) {
		mPage = Page.INITIAL;
		if (arg0)
			mAdapter.reset();
	}
}