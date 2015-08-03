require "spec_helper"
require_relative 'helpers/test_helpers' 

describe 'when search for Digital River German phone number from Google', :type => :feature do
	before :all do
		visit 'http://www.google.com'
		fill_in 'q', with: 'Digital River cologne phone'
		click_button 'Google'
		click_name 'Digital River GmbH - Share-it'
	end
	it 'should display the phone number' do
		expect(current_url).to eq('https://secure.shareit.com/shareit/impressum.html')
		expect(page.text).to match /Cologne, Germany/
		expect(page.text).to match /\+49 221 31088-30/
	end
end
