require "spec_helper"
require_relative 'helpers/test_helpers' 

describe 'when search for Digital River German Address from Google', :type => :feature do
	before :all do
		visit 'http://www.google.com'
		fill_in 'q', with: 'Digital River'
		click_button 'Google'
		click_name 'Digital River, Inc.'
	end
	it 'should display the Adress' do
		expect(current_url).to eq('http://www.digitalriver.com/')
		click_name 'Contact Us'
		page.evaluate_script("jQuery('#emea').click()")
		find('div.office-wrapper[data-officeid="cologne"]')
		within('div.office-wrapper[data-officeid="cologne"]') do
			expect(page).to have_content('Germany')
			expect(page).to have_content('Cologne 50933')
		end
	end
end
