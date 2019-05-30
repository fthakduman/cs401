	package com.composite.modelbasedtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

public class Model implements FsmModel {

	public enum Page {
		INITIAL, COMPOSITE, YKB, BANK1, BANK2, BANK3, BANK4
	}

	private Page mPage = Page.INITIAL;

	private Adapter mAdapter = new Adapter();

	@Action
	public void composite() {
		mAdapter.composite();
		mPage = Page.COMPOSITE;
	}

	public boolean compositeGuard() {
		return mPage == Page.INITIAL;
	}
	
	@Action
	public void ykb() {
		mAdapter.ykb();
		mPage = Page.YKB;
	}

	public boolean ykbGuard() {
		return mPage == Page.COMPOSITE;
	}

	@Action
	public void bank1() {
		mAdapter.bank1();
		mPage = Page.BANK1;
	}

	public boolean bank1Guard() {
		return mPage == Page.YKB;
	}

	@Action
	public void bank2() {
		mAdapter.bank2();
		mPage = Page.BANK2;
	}

	public boolean bank2Guard() {
		return mPage == Page.BANK1;
	}

	@Action
	public void bank3() {
		mAdapter.bank3();
		mPage = Page.BANK3;
	}

	public boolean bank3Guard() {
		return mPage == Page.BANK2;
	}

	@Action
	public void bank4() {
		mAdapter.bank4();
		mPage = Page.BANK4;
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