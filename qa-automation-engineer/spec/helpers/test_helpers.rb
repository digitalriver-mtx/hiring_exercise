def click_name(name)
	begin
		click_on name, match: :first
	rescue Capybara::Poltergeist::TimeoutError
	end	
end
